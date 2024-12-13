package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Test;
import com.example.appbdcs.repository.ITestRepository;
import com.example.appbdcs.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class TestService implements ITestService {

    @Autowired
    private ITestRepository testRepository;



    @Override
    public List<Test> getTestsByCourse(Integer courseId) {
        return testRepository.findByCourseId(courseId);
    }

    // Thêm bài test mới
    @Override
    public Test addTest(Test test) {
        testRepository.addTest(test.getTestName(), test.getProgressThreshold(), test.getPassScore(), test.getCourse().getCourseId());
        return test; // Trả về bài test vừa thêm
    }

    // Sửa bài test
    @Override
    public Test updateTest(Integer id, Test test) {
        Optional<Test> existingTest = testRepository.findById(id);
        if (existingTest.isPresent()) {
            testRepository.updateTest(id, test.getTestName(), test.getProgressThreshold(), test.getPassScore(), test.getCourse().getCourseId());
            // Cập nhật lại thông tin của đối tượng đã thay đổi
            Test updatedTest = existingTest.get();
            updatedTest.setTestName(test.getTestName());
            updatedTest.setProgressThreshold(test.getProgressThreshold());
            updatedTest.setPassScore(test.getPassScore());
            updatedTest.setCourse(test.getCourse());
            return updatedTest;
        }
        return null; // Nếu không tìm thấy bài test
    }

    // Xoá bài test
    @Transactional
    @Override
    public boolean deleteTest(Integer id) {
        testRepository.deleteById(id); // Xoá bài test
        return true;
    }



    // Lấy tất cả bài test
    @Override
    public List<Test> getAllTests() {
        return testRepository.findAllTests(); // Lấy tất cả bài test
    }

    // Lấy bài test theo id
    @Override
    public Test getTestById(Integer id) {
        return testRepository.findById(id).orElse(null); // Lấy bài test theo id
    }
}
