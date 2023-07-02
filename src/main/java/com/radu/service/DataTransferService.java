package com.radu.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.radu.model.History;
import com.radu.model.Member;
import com.radu.repository.HistoryRepository;
import com.radu.repository.MemberRepository;

@Service
public class DataTransferService {
    private final MemberRepository memberRepository;
    private final HistoryRepository historyRepository;

    public DataTransferService(MemberRepository memberRepository, HistoryRepository historyRepository) {
        this.memberRepository = memberRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public void transferDataFromMembersToHistory() {
        // Get entries from members where datasfarsit has passed
        List<Member> memberList = memberRepository.findByDateEndBefore(LocalDate.now());

        for (Member members : memberList) {
            // Transfer data to history
            History history = new History();
            history.setDateStart(members.getDateStart());
            history.setDateEnd(members.getDateEnd());
            history.setIdPackage(members.getIdSubscription());
            history.setIdMember(members.getId());
            history.setObservations(members.getObservations());
            // Set other columns in history as needed

            historyRepository.save(history);

            // Set columns to null in Members
            members.setDateStart(null);
            members.setDateEnd(null);
            // Set other columns in Members as needed

            memberRepository.save(members);
        }
    }

    @Scheduled(cron = "0 0 0 * * *") // Run every day at midnight
    public void scheduleDataTransfer() {
        transferDataFromMembersToHistory();
    }
}
