package com.gmail.merikbest2015.ecommerce.configuration;

import com.gmail.merikbest2015.ecommerce.repository.OrderItemRepository;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitialDataCreatorEditor {

    private final PerfumeService perfumeService;
    private final OrderService orderService;
    private final OrderItemRepository orderItemRepository;
//    private final

    @PostConstruct
    public void init() {
//        perfumeService.savePerfume()
//        orderItemRepository.deleteAll();
//        perfumeService.deletePerfume(66L);
//        perfumeService.deletePerfume(67L);
//        perfumeService.deletePerfume(109L);
//        perfumeService.deletePerfume(101L);
//        perfumeService.deletePerfume(98L);
//        perfumeService.deletePerfume(99L);
//        perfumeService.deletePerfume(100L);
//        perfumeService.deletePerfume(69L);
//        perfumeService.deletePerfume(83L);
//        perfumeService.deletePerfume(84L);
//        perfumeService.deletePerfume(68L);
//        perfumeService.deletePerfume(29L);
//        perfumeService.deletePerfume(L);
    }
}
