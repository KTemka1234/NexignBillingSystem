package ru.learnhub.switchboard.service;

import ru.learnhub.commondto.dto.CallDataRecord;
import ru.learnhub.commondto.dto.CallType;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CDRFileGenerator {

    private static final int MAX_CALLS_COUNT = 5;
    private static final int MONTHS_BEFORE = 6;
    private static final int MAX_CALL_DURATION = 360; // в минутах

    private final Random random;
    private final Set<String> generatedNumbers;

    public CDRFileGenerator() {
        this.random = new Random();
        this.generatedNumbers = new HashSet<>();
    }

    public File generateCDRFile(String filePath, List<CallDataRecord> cdrList) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        File file = new File(filePath);
        file.createNewFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (CallDataRecord cdr : cdrList) {
                writer.write(cdr.getCallType().getIndex());
                writer.write(", ");
                writer.write(cdr.getPhoneNumber());
                writer.write(", ");
                writer.write(cdr.getCallStartTime().format(formatter));
                writer.write(", ");
                writer.write(cdr.getCallEndTime().format(formatter));
                writer.newLine();
            }
        }
        return file;
    }

    public List<CallDataRecord> generateCDRList(int numbersCount) {
        List<CallDataRecord> generatedCDRList = new LinkedList<>();
        for (int i = 0; i < numbersCount; i++) {
            String newNumber = getRandomUniqueNumber();
            generatedCDRList.addAll(getRandomCallsForSelectedPhoneNumber(newNumber));
        }
        Collections.shuffle(generatedCDRList); // перемешиваем список, чтобы одинаковые номера не шли подряд
        return generatedCDRList;
    }

    // Создание списка звонков для выбранного номера
    private List<CallDataRecord> getRandomCallsForSelectedPhoneNumber(String number) {
        int callsCount = random.nextInt(MAX_CALLS_COUNT) + 1;
        List<CallDataRecord> generatedPhoneNumberCalls = new LinkedList<>();
        LocalDateTime startDateTime = LocalDate.now().minusMonths(MONTHS_BEFORE).atStartOfDay();
        int totalMinutes = MONTHS_BEFORE * 30 * 24 * 60; // Кол-во минут в месяце

        // Макс. кол-во минут между звонками
        int maxMinsBetweenCalls = (totalMinutes - MAX_CALL_DURATION * callsCount) / callsCount;
        for (int i = 0; i < callsCount; i++) {
            String callType = getRandomCallType();
            List<LocalDateTime> callStartEndTime = getRandomCallDuration(startDateTime, maxMinsBetweenCalls);
            LocalDateTime callStartTime = callStartEndTime.get(0);
            LocalDateTime callEndTime = callStartEndTime.get(1);

            startDateTime = callEndTime;
            generatedPhoneNumberCalls.add(new CallDataRecord(
                    CallType.getInstanceByIndex(callType),
                    number,
                    callStartTime,
                    callEndTime
            ));
        }
        return generatedPhoneNumberCalls;
    }

    private String getRandomCallType() {
        return "0".concat(String.valueOf(random.nextInt(2) + 1));
    }

    private String getRandomUniqueNumber() {
        StringBuilder sb = new StringBuilder(11);
        // Список кодов оператора "Ромашка"
        ArrayList<String> operator_codes = new ArrayList<>(Arrays.asList("373", "521", "191"));
        do {
            sb.append(7); // Первая цифра номера всегда одинаковая
            sb.append(operator_codes.get(random.nextInt(3)));
            for (int i = 0; i < 7; i++) { // Добавляем ещё 10 недостающих цифр номера
                sb.append(random.nextInt(10));
            }
        } while (generatedNumbers.contains(sb.toString()));
        generatedNumbers.add(sb.toString());
        return sb.toString();
    }

    // Случайное время звонка в диапазоне между текущим днём и N месяцев ранее
    private List<LocalDateTime> getRandomCallDuration(LocalDateTime startDate, int maxMinsBetweenCalls) {
        List<LocalDateTime> startEndTime = new ArrayList<>(2);
        LocalDateTime callStartTime = startDate.plus(Duration.ofSeconds(random.nextInt(maxMinsBetweenCalls * 60)));
        LocalDateTime callEndTime = callStartTime.plus(Duration.ofSeconds(random.nextInt(MAX_CALL_DURATION * 60)));
        startEndTime.add(callStartTime);
        startEndTime.add(callEndTime);
        return startEndTime;
    }
}
