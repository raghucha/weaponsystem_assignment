package com.weponsystem.weaponsystem.controllers;

import com.weponsystem.weaponsystem.exception.ConstraintViolationExceptionHandle;
import com.weponsystem.weaponsystem.model.Command;
import com.weponsystem.weaponsystem.model.ExcutiveSummary;
import com.weponsystem.weaponsystem.model.TargetSummary;
import com.weponsystem.weaponsystem.model.WeaponSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Validated
public class CommandController {
    private final Logger logger = LoggerFactory.getLogger(CommandController.class);

    @Autowired
    private List<Command> commandList;

    @GetMapping("/commands")
    public ResponseEntity<List<Command>> getAllCommands() {
        return new ResponseEntity<>(commandList, HttpStatus.OK);

    }

    @PostMapping("/addCommands")
    public ResponseEntity<?> addNewCommands(@Valid @RequestBody
                                                    List<Command> commands) {
        logger.info("New Commands sent");
        if (commands.size() != 0) {

            List<Command> val = commands.stream().map(c -> {

                Command command = new Command(c.getWeaponSystem(), c.getBattleship(), c.getTarget(), c.getQuantity(), c.getRate());
                return command;
            }).collect(Collectors.toList());

            commandList.addAll(val);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/executiveSummary")
    @Scheduled(fixedRate = 60000)
    public ResponseEntity<ExcutiveSummary> getExecutiveSummary() {
        logger.info("Executive Summary called");
        ExcutiveSummary summary = new ExcutiveSummary();
        Map<Command, Integer> map = new HashMap<>();
        if (commandList.size() != 0) {
            int numberOfCommands = commandList.size();
            double totalRate = 0.0;
            double totalQuantity = 0.0;
            for (Command command : commandList) {
                totalQuantity += command.getQuantity();
                totalRate += command.getRate();
                if (map.containsKey(command)) {
                    map.put(command, map.getOrDefault(command, 0) + 1);
                } else {
                    map.put(command, 1);
                }
            }

            Set<Command> numberOFAttacks = map.entrySet().stream()
                    .filter(entry -> entry.getValue() >= 2)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());

            summary.setTotalCommands(numberOfCommands);
            summary.setAverageRate(totalRate / numberOfCommands);
            summary.setQuantity(totalQuantity / numberOfCommands);
            summary.setNumberOfAttacks(numberOFAttacks.size());

        } else {
            summary.setTotalCommands(Double.valueOf(0));
            summary.setAverageRate(Double.valueOf(0));
            summary.setQuantity(Double.valueOf(0));
            summary.setNumberOfAttacks(0);
        }


        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @GetMapping("/targetSummary")
    @Scheduled(fixedRate = 60000)
    public ResponseEntity<List<TargetSummary>> getTargetSummary() {
        logger.info("Target Summary called");
        Map<String, List<Command>> targetMap = commandList.stream().collect(Collectors.groupingBy((Command::getTarget)));

        List<TargetSummary> targetSummaries = new ArrayList<>();

        for (Map.Entry<String, List<Command>> item :
                targetMap.entrySet()) {
            TargetSummary sum = new TargetSummary(item.getKey(), item.getValue());
            targetSummaries.add(sum);

        }

        return new ResponseEntity<>(targetSummaries, HttpStatus.OK);
    }

    @GetMapping("/weaponSummary")
    @Scheduled(fixedRate = 60000)
    public ResponseEntity<List<WeaponSummary>> getWeaponsSummary() {
        logger.info("Weapon Summary called");
        Map<String, List<Command>> targetMap = commandList.stream().collect(Collectors.groupingBy((Command::getWeaponSystem)));

        List<WeaponSummary> targetSummaries = new ArrayList<>();

        for (Map.Entry<String, List<Command>> item :
                targetMap.entrySet()) {
            WeaponSummary sum = new WeaponSummary(item.getKey(), item.getValue());
            targetSummaries.add(sum);

        }

        return new ResponseEntity<>(targetSummaries, HttpStatus.OK);
    }


}
