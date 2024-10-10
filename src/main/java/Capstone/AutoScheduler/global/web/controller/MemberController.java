package Capstone.AutoScheduler.global.web.controller;

import Capstone.AutoScheduler.global.apiPayload.code.status.SuccessStatus;
import Capstone.AutoScheduler.global.converter.MemberConverter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import Capstone.AutoScheduler.global.apiPayload.ApiResponse;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.service.MemberService.MemberCommandService;
import Capstone.AutoScheduler.global.service.MemberService.MemberQueryService;
import Capstone.AutoScheduler.global.web.dto.Member.MemberRequestDTO;
import Capstone.AutoScheduler.global.web.dto.Member.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/members")
@Slf4j
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // 회원가입
    @Operation(summary = "회원가입", description = "캘린더 사용을 위한 회원가입을 진행합니다.")
    @PostMapping("/sign_up")
    public ApiResponse<MemberResponseDTO.CreateMemberResultDTO> createMember(
            @RequestBody MemberRequestDTO.CreateMemberRequestDTO request
    ) {
        Member newMember = memberCommandService.createMember(request);
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_OK,MemberConverter.toCreateResultDTO(newMember));
    }

    // 로그인
    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    @PostMapping("/sign_in")
    public ApiResponse<MemberResponseDTO.SignInResultDTO> signIn(
            @RequestBody MemberRequestDTO.SignInRequestDTO request
    ) {
        Member findMember = memberQueryService.signIn(request);
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_OK, MemberConverter.toSignInResultDTO(findMember));
    }



}
