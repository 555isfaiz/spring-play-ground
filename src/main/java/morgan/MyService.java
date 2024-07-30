package morgan;

import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    String serviceKey = "my-service";
    String serviceVal = "f1@g";
    String lastModifier = "";

    @Autowired
    MyDataRepo dataRepo;

    @Autowired
    ValueProcessor valueProcessor;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MyService.class);

    public void setNewValue(String newVal, String modifier) {
        newVal = valueProcessor.process(newVal);
        this.serviceVal = newVal;
        this.lastModifier = modifier;
        logger.info("just set a new value: " + this.serviceVal);

        MyData newRecord = new MyData();
        newRecord.setValue(newVal);
        newRecord.setModifier(modifier);
        newRecord.setTimestamp(System.currentTimeMillis());
        dataRepo.save(newRecord);
    }

    public Optional<MyData> getHistoryById(long id) {
        return dataRepo.findById(id);
    }

    public List<MyData> getAllHistory() {
        return dataRepo.findAll();
    }

    public void deleteHistoryById(long id) {
        dataRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        var history = dataRepo.findAll();
        if (history.size() <= 0)
            return;

        history.sort((a, b) -> (int) (a.getTimestamp() - b.getTimestamp()));

        var latest = history.get(history.size() - 1);
        this.serviceVal = latest.getValue();
        this.lastModifier = latest.getModifier();

        logger.info(
                "Init done, value: " + this.serviceVal + ", modifier: " + this.lastModifier + ".");
    }
}
