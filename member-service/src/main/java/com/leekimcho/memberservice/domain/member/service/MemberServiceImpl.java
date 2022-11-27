package com.leekimcho.memberservice.domain.member.service;

import com.leekimcho.memberservice.domain.member.dto.MemberDto;
import com.leekimcho.memberservice.domain.member.entity.Member;
import com.leekimcho.memberservice.domain.member.exception.NotExistMemberException;
import com.leekimcho.memberservice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Slf4j
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String membername) throws UsernameNotFoundException {
        Member Member = memberRepository.findByEmail(membername)
                .orElseThrow(() -> new UsernameNotFoundException("Member not found in the database"));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Member.getRoleType().toString()));
        return new org.springframework.security.core.userdetails.User(Member.getId().toString(), Member.getPassword(), authorities);
    }

    @Override
    public MemberDto findMemberByMemberId(Long MemberId) {
        Member member = memberRepository.findById(MemberId)
                .orElseThrow(() -> new NotExistMemberException("존재하지 않는 사용자 입니다."));

        return new MemberDto(member);
    }

    @Override
    public List<MemberDto> findMemberByMemberIds(List<Long> MemberIds) {
        return memberRepository.findAllById(MemberIds)
                .stream()
                .map(MemberDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto register(MemberDto member) {
        return MemberDto.of(memberRepository.save(member.toEntity()));
    }

    @Override
    public Optional<Member> findMemberByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail);
    }

    @Override
    public void registerAdmin(MemberDto dto, boolean flag) {
        memberRepository.save(new Member(dto, flag));
    }

}
