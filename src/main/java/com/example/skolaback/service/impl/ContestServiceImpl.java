package com.example.skolaback.service.impl;

import com.example.skolaback.model.dto.application.CreateContestApplicationDTO;
import com.example.skolaback.model.dto.contest.ContestResponseDTO;
import com.example.skolaback.model.dto.user.UserResponseDTO;
import com.example.skolaback.model.entity.*;
import com.example.skolaback.model.enumerations.ApplicationStatus;
import com.example.skolaback.model.enumerations.ContestStatus;
import com.example.skolaback.model.enumerations.SchoolType;
import com.example.skolaback.model.mapper.ExtendedModelMapper;
import com.example.skolaback.repository.ContestApplicationRepository;
import com.example.skolaback.repository.ContestRepository;
import com.example.skolaback.repository.CourseQuotaRepository;
import com.example.skolaback.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.skolaback.security.AuthHelper.authUser;

@Service
public class ContestServiceImpl implements ContestService {

//    @Value("${sso.url}")
    private String ssoUrl;
    private final ExtendedModelMapper modelMapper;
    private final ContestRepository contestRepository;
    private final CourseQuotaRepository courseQuotaRepository;
    private final ContestApplicationRepository contestApplicationRepository;
    private final SchoolService schoolService;
    private final StudentService studentService;
    private final DiplomaService diplomaService;
    private final CourseService courseService;

    public ContestServiceImpl(SchoolService schoolService, CourseQuotaRepository courseQuotaRepository, ContestRepository contestRepository, ExtendedModelMapper modelMapper, StudentService studentService, DiplomaService diplomaService, ContestApplicationRepository contestApplicationRepository, CourseService courseService) {
        this.schoolService = schoolService;
        this.courseQuotaRepository = courseQuotaRepository;
        this.contestRepository = contestRepository;
        this.modelMapper = modelMapper;
        this.studentService = studentService;
        this.diplomaService = diplomaService;
        this.contestApplicationRepository = contestApplicationRepository;
        this.courseService = courseService;
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

            if (user == null || !Objects.equals(user.getFatherJmbg(), authUser().getUsername())) {
                return 0;
            }
            //TODO change role in sso-server to student

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
}
