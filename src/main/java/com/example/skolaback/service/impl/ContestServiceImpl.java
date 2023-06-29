package com.example.skolaback.service.impl;

import com.example.skolaback.model.dto.application.CreateContestApplicationDTO;
import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.dto.student.ChangeStudentRoleDTO;
import com.example.skolaback.model.dto.user.UserResponseDTO;
import com.example.skolaback.model.entity.*;
import com.example.skolaback.model.enumerations.ApplicationStatus;
import com.example.skolaback.model.enumerations.ContestStatus;
import com.example.skolaback.model.enumerations.SchoolType;
import com.example.skolaback.model.mapper.ExtendedModelMapper;
import com.example.skolaback.repository.ContestApplicationRepository;
import com.example.skolaback.repository.ContestRepository;
import com.example.skolaback.repository.CourseQuotaRepository;
import com.example.skolaback.repository.NotificationRepository;
import com.example.skolaback.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

import static com.example.skolaback.security.AuthHelper.authUser;

@Service
public class ContestServiceImpl implements ContestService {

//    @Value("${sso.url}")
    @Value("http://localhost:9090")
    private String ssoUrl;
    private final ExtendedModelMapper modelMapper;
    private final ContestRepository contestRepository;
    private final CourseQuotaRepository courseQuotaRepository;
    private final ContestApplicationRepository contestApplicationRepository;
    private final SchoolService schoolService;
    private final StudentService studentService;
    private final DiplomaService diplomaService;
    private final CourseService courseService;
    private final NotificationRepository notificationRepository;

    public ContestServiceImpl(SchoolService schoolService, CourseQuotaRepository courseQuotaRepository, ContestRepository contestRepository, ExtendedModelMapper modelMapper, StudentService studentService, DiplomaService diplomaService, ContestApplicationRepository contestApplicationRepository, CourseService courseService, NotificationRepository notificationRepository) {
        this.schoolService = schoolService;
        this.courseQuotaRepository = courseQuotaRepository;
        this.contestRepository = contestRepository;
        this.modelMapper = modelMapper;
        this.studentService = studentService;
        this.diplomaService = diplomaService;
        this.contestApplicationRepository = contestApplicationRepository;
        this.courseService = courseService;
        this.notificationRepository = notificationRepository;
    }


    @Override
    public Contest getById(long id) {
        return contestRepository.getById(id);
    }

    @Override
    public ContestResponseDTO createContest(long schoolId, Contest contest) {
        School school = schoolService.getById(schoolId);
        contest.setSchool(school);

        contest.setContestStatus(ContestStatus.AKTIVAN);

        if (school.getType().equals(SchoolType.SREDNJA) && !contest.getQuotas().isEmpty()) {
            contest.setPrimarySchoolQuota(null);
            courseQuotaRepository.saveAll(contest.getQuotas());
        }

        contestRepository.save(contest);

        return modelMapper.map(contest, ContestResponseDTO.class);
    }

    @Override
    public long createApplication(long contestId, CreateContestApplicationDTO createContestApplicationDTO) {
        Contest contest = contestRepository.getById(contestId);
        ContestApplication contestApplication = new ContestApplication();

        if (LocalDate.now().isBefore(contest.getStartDate()) || LocalDate.now().isAfter(contest.getEndDate())) {
            return 0;
        }

        contestApplication.setContest(contest);
        contestApplication.setApplicationStatus(ApplicationStatus.NA_CEKANJU);

        if (contest.getSchool().getType() == SchoolType.OSNOVNA) {
            RestTemplate restTemplate = new RestTemplate();

            UserResponseDTO user = restTemplate.
                    getForObject(String.format("%s/api/users/%s", ssoUrl,
                                    createContestApplicationDTO.getChildJmbg()), UserResponseDTO.class);

            if(user == null) {
                return 0;
            }
            boolean parent =
                    (user.getFatherJmbg() != null && Objects.equals(user.getFatherJmbg(), authUser().getUsername()))
                    || (user.getMotherJmbg() != null && Objects.equals(user.getMotherJmbg(), authUser().getUsername()));

            if (!parent) {
                return 0;
            }

            if (checkIfApplicationExist(contest, createContestApplicationDTO.getChildJmbg())) {
                return 0;
            }

            Student student  = studentService.createStudent(createContestApplicationDTO, contest.getSchool());
            contestApplication.setStudent(student);
        }

        if (contest.getSchool().getType() == SchoolType.SREDNJA) {
            Student student = studentService.getByJMBG(authUser().getUsername());
            Course firstWish = courseService.getById(createContestApplicationDTO.getFirstWish().getId());

            contestApplication.setStudent(student);
            contestApplication.setFirstWish(firstWish);

            if (checkIfApplicationExist(contest, student.getJmbg())) {
                return 0;
            }

            if(!diplomaService.checkExistDiploma(student.getJmbg(), SchoolType.OSNOVNA)) {
                return 0;
            }

            contestApplication.setRangPoints(studentService.calculateRangPoints(student.getId()));
        }

        contestApplicationRepository.save(contestApplication);

        return contestApplication.getId();
    }

    @Override
    public boolean checkIfApplicationExist(Contest contest, String studentJmbg) {

        return contestApplicationRepository.getByStudentJmbgAndContestId(studentJmbg, contest.getId())
                .isPresent();

    }

    @Override
    public List<Contest> getContestByStatus(ContestStatus contestStatus) {
        return contestRepository.getContestByStatus(contestStatus);
    }

    @Override
    public List<ContestApplication> getContestApplicationByCourse(long contestId, long courseId) {
        return contestApplicationRepository.getContestApplicationsByCourse(contestId, courseId);
    }

    @Override
    public List<ContestApplication> getContestApplication(long contestId) {
        return contestApplicationRepository.getContestApplications(contestId);
    }

    @Override
    public void closeContest(long contestId) {
        Contest contest = contestRepository.getById(contestId);
        contest.setContestStatus(ContestStatus.ZATVOREN);

        contestRepository.save(contest);

        if (contest.getSchool().getType() == SchoolType.OSNOVNA) {
            List<ContestApplication> contestApplications =
                    contestApplicationRepository.getTopNContestApplications(contestId, contest.getPrimarySchoolQuota());

            for (ContestApplication application : contestApplications) {
                Student student = application.getStudent();
                if (student.getSchool() != null) {
                    Notification notification = Notification.builder()
                            .student(application.getStudent())
                            .date(new Date())
                            .text("Vec imate skolu, ne mozete opet biti upisani u osnovnu!")
                            .link("/contests/"
                                    + contest.getId() + "/courses/" +
                                    application.getFirstWish().getId() + "/ranking")
                            .build();
                    notificationRepository.save(notification);
                    continue;
                }
                application.setApplicationStatus(ApplicationStatus.PRIMLJEN);
                contestApplicationRepository.save(application);


                student.setSchool(contest.getSchool());
                studentService.save(student);

                Notification notification = Notification.builder()
                        .student(application.getStudent())
                        .date(new Date())
                        .text("Cestitamo upisali ste osnovnu skolu, pogledajte rezultate!")
                        .link("/contests/"
                                + contest.getId() + "/courses/" +
                                application.getFirstWish().getId() + "/ranking")
                        .build();
                notificationRepository.save(notification);
            }

            List<ContestApplication> rejectedApplications =
                    contestApplicationRepository.getOnHoldContestApplications(contestId);

            for (ContestApplication application : rejectedApplications) {
                application.setApplicationStatus(ApplicationStatus.ODBIJEN);
                contestApplicationRepository.save(application);

                Notification notification = Notification.builder()
                        .student(application.getStudent())
                        .date(new Date())
                        .text("Nazalost niste upisali osnovnu skolu, pogledajte rezultate!")
                        .link("/contests/"
                                + contest.getId() + "/courses/" +
                                application.getFirstWish().getId() + "/ranking")
                        .build();
                notificationRepository.save(notification);
            }
        }
        if (contest.getSchool().getType() == SchoolType.SREDNJA) {
            for (CourseQuota courseQuota : contest.getQuotas()) {
                List<ContestApplication> contestApplications =
                        contestApplicationRepository.
                                getTopNContestApplicationsByCourse
                                        (contestId, courseQuota.getCourse().getId(), courseQuota.getQuota());
                for (ContestApplication application : contestApplications) {
                    Student student = application.getStudent();
                    if (student.getSchool().getType() == SchoolType.SREDNJA) {
                        Notification notification = Notification.builder()
                                .student(application.getStudent())
                                .date(new Date())
                                .text("Vec imate srednju skolu, ne mozete opet upisati!")
                                .link("/contests/"
                                        + contest.getId() + "/courses/" +
                                        application.getFirstWish().getId() + "/ranking")
                                .build();
                        notificationRepository.save(notification);
                        continue;
                    }

                    application.setApplicationStatus(ApplicationStatus.PRIMLJEN);
                    contestApplicationRepository.save(application);

                    student.setSchool(contest.getSchool());
                    student.setCourse(application.getFirstWish().getName());
                    studentService.save(student);

                    Notification notification = Notification.builder()
                            .student(application.getStudent())
                            .date(new Date())
                            .text("Cestitamo upisali ste srednju skolu, pogledajte rezultate!")
                            .link("/contests/"
                                    + contest.getId() + "/courses/" +
                                    application.getFirstWish().getId() + "/ranking")
                            .build();
                    notificationRepository.save(notification);
                }

                List<ContestApplication> rejectedApplications =
                        contestApplicationRepository.
                                getOnHoldContestApplicationsByCourse(contestId, courseQuota.getCourse().getId());

                for (ContestApplication application : rejectedApplications) {
                    application.setApplicationStatus(ApplicationStatus.ODBIJEN);
                    contestApplicationRepository.save(application);

                    Notification notification = Notification.builder()
                            .student(application.getStudent())
                            .date(new Date())
                            .text("Nazalost niste upisali osnovnu skolu, pogledajte rezultate!")
                            .link("/contests/"
                                    + contest.getId() + "/courses/" +
                                    application.getFirstWish().getId() + "/ranking")
                            .build();
                    notificationRepository.save(notification);
                }
            }
        }
    }

    public void changeRoleInSSO(Student student, School school) {
        ChangeStudentRoleDTO serviceRole = new ChangeStudentRoleDTO();
        serviceRole.setRole("ROLE_STUDENT");
        serviceRole.setService("school");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("schoolId", school.getId());
        serviceRole.setAttributes(attributes);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(String.format("%s/api/users/%s/serviceRoles", ssoUrl, student.getJmbg()), serviceRole);
    }
}
