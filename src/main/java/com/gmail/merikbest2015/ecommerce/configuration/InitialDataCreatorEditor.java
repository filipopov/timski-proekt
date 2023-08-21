package com.gmail.merikbest2015.ecommerce.configuration;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
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
        // 21 23
        Perfume perfume = perfumeService.getPerfumeById(21L);
        perfume.setFilename("https://www.mojazimnica.rs/wp-content/uploads/2020/10/sirup-od-malina.jpg.pagespeed.ce.pFIWEUwInJ.jpg");
        perfumeService.save(perfume);

//        perfumeService.savePerfume()
//        orderItemRepository.deleteAll();
//        perfumeService.deletePerfume(111L);
//        perfumeService.deletePerfume(112L);
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
