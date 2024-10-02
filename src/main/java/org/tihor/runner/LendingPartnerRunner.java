package org.tihor.runner;

import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.tihor.entity.LendingPartnerEntity;
import org.tihor.enums.LendingPartnerType;
import org.tihor.repository.LendingPartnerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Lending partner runner.
 */
@Component
public class LendingPartnerRunner implements ApplicationRunner {
    /**
     * The Lending partner repository.
     */
    @Resource
    private LendingPartnerRepository lendingPartnerRepository;

    /**
     * The Data.
     */
    private final List<Object[]> data = new ArrayList<>();

    /**
     * Instantiates a new Lending partner runner.
     */
    public LendingPartnerRunner() {
        data.add(new Object[]{"HDFC", LendingPartnerType.BANK, "Hyderabad"});
        data.add(new Object[]{"Bajaj Finance", LendingPartnerType.PRIVATE_FINANCE, "Mumbai"});
        data.add(new Object[]{"VISA", LendingPartnerType.BANK, "Hyderabad"});
    }

    /**
     * Run.
     *
     * @param args the args
     * @throws Exception the exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        var entities = data.stream()
                .filter(each -> {
                    var optional = lendingPartnerRepository.findByNameAndTypeAndAddress((String) each[0], (LendingPartnerType) each[1], (String) each[2]);
                    return optional.isEmpty();
                })
                .map(each -> prepareEntity((String) each[0], (LendingPartnerType) each[1], (String) each[2]))
                .toList();

        lendingPartnerRepository.saveAll(entities);
    }

    /**
     * Prepare entity lending partner entity.
     *
     * @param name    the name
     * @param type    the type
     * @param address the address
     * @return the lending partner entity
     */
    private LendingPartnerEntity prepareEntity(final String name, final LendingPartnerType type, final String address) {
        return LendingPartnerEntity.builder()
                .name(name)
                .type(type)
                .address(address)
                .build();
    }
}
