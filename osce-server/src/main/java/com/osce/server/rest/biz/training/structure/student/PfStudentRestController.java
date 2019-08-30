package com.osce.server.rest.biz.training.structure.student;

import com.osce.api.biz.training.structure.student.PfStudentService;
import com.osce.dto.biz.training.structure.student.StudentDepartBachDto;
import com.osce.dto.biz.training.structure.student.StudentDepartDto;
import com.osce.dto.biz.training.structure.student.StudentDto;
import com.osce.dto.biz.training.structure.student.StudentMoveDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.dto.user.login.RegisterDto;
import com.osce.enums.OperationTypeEnum;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PfStudentRestController
 * @Description: 学员管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@RestController
public class PfStudentRestController extends BaseController {

    @Reference
    private PfStudentService pfStudentService;

    /**
     * 新增学员信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/student/add")
    public ResultObject addStudent(@RequestBody StudentDepartDto dto) {
        /* 参数校验 */
        dto.getRegisterInfo().setOperator(CurrentUserUtils.getCurrentUsername());
        dto.getRegisterInfo().setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addStudent", ResultObject.DATA_TYPE_OBJECT,
                pfStudentService.addStudent(dto));
    }

    /**
     * 新增学员信息 - 批量操作
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/student/bach/add")
    public ResultObject bachAddStudent(@RequestBody StudentDepartBachDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdDepart() != null, "idDepart");
        Assert.isTrue(dto.getIdGrade() != null, "idGrade");
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getUsers()), "users");
        List<RegisterDto> users = dto.getUsers();
        List<RegisterDto> errorData = new ArrayList<>();
        for (RegisterDto registerDto : users) {
            StudentDepartDto departDto = new StudentDepartDto();
            departDto.setIdDepart(dto.getIdDepart());
            departDto.setIdGrade(dto.getIdGrade());

            registerDto.setOperator(CurrentUserUtils.getCurrentUsername());
            registerDto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
            departDto.setRegisterInfo(registerDto);
            try {
                pfStudentService.addStudent(departDto);
            } catch (Exception e) {
                registerDto.setImportErrorMsg(e.getMessage());
                errorData.add(registerDto);
            }
        }

        return ResultObject.createSuccess("bachAddStudent", ResultObject.DATA_TYPE_OBJECT, errorData);
    }

    /**
     * 编辑学员信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/student/edit")
    public ResultObject editStudent(@RequestBody StudentDepartDto dto) {
        /* 参数校验 */
        dto.getRegisterInfo().setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editStudent", ResultObject.DATA_TYPE_OBJECT,
                pfStudentService.addStudent(dto));

    }

    /**
     * 删除学员信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/student/del")
    public ResultObject delStudent(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfStudentService.delStudent(dto) ? ResultObject.createSuccess("delStudent", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delQuestion", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用学员信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/student/updateStatus")
    public ResultObject updateStudentStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        dto.setExtId(CurrentUserUtils.getCurrentUserIdOrg());
        return pfStudentService.delStudent(dto) ? ResultObject.createSuccess("updateStudentStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateStudentStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 获取班级下所有学员信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/student/list/byGradeId")
    public ResultObject listStudentByIdGrade(@RequestBody StudentDto dto) {
        /* 参数校验 */
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("listStudentByIdGrade", ResultObject.DATA_TYPE_OBJECT,
                pfStudentService.listStudentByIdGrade(dto));

    }


    /**
     * 往届学员迁移
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/student/move")
    public ResultObject moveStudent(@RequestBody StudentMoveDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getIdUsers()), "idUsers");
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getIdDeparts()), "idDeparts");
        return ResultObject.createSuccess("moveStudent", ResultObject.DATA_TYPE_OBJECT,
                pfStudentService.moveStudent(dto));

    }



}
