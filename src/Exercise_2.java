import java.util.ArrayList;
import java.util.List;

interface IPart {
    String getName();
}

interface IWorker {
    void work(House house);
}

class Basement implements IPart {
    public String getName() { return "Фундамент"; }
}

class Wall implements IPart {
    public String getName() { return "Стіна"; }
}

class Door implements IPart {
    public String getName() { return "Двері"; }
}

class Window implements IPart {
    public String getName() { return "Вікно"; }
}

class Roof implements IPart {
    public String getName() { return "Дах"; }
}

class House {
    private final List<IPart> parts = new ArrayList<>();

    public void addPart(IPart part) {
        parts.add(part);
    }

    public List<IPart> getParts() {
        return parts;
    }
}

class Worker implements IWorker {
    @Override
    public void work(House house) {
        if (house.getParts().stream().filter(p -> p instanceof Basement).count() < 1) {
            house.addPart(new Basement());
        } else if (house.getParts().stream().filter(p -> p instanceof Wall).count() < 4) {
            house.addPart(new Wall());
        } else if (house.getParts().stream().filter(p -> p instanceof Window).count() < 4) {
            house.addPart(new Window());
        } else if (house.getParts().stream().filter(p -> p instanceof Door).count() < 1) {
            house.addPart(new Door());
        } else if (house.getParts().stream().filter(p -> p instanceof Roof).count() < 1) {
            house.addPart(new Roof());
        }
    }
}

class TeamLeader implements IWorker {
    @Override
    public void work(House house) {
        System.out.println("Звіт про стан будівництва:");
        for (IPart part : house.getParts()) {
            System.out.println("- " + part.getName());
        }
    }
}

class Team {
    private final List<IWorker> workers = new ArrayList<>();
    private final TeamLeader teamLeader;

    public Team(int workerCount) {
        for (int i = 0; i < workerCount; i++) {
            workers.add(new Worker());
        }
        this.teamLeader = new TeamLeader();
    }

    public void build(House house) {
        while (house.getParts().size() < 10) {
            for (IWorker worker : workers) {
                worker.work(house);
            }
            teamLeader.work(house);
        }
        System.out.println("Будівництво будинку завершено!");
    }
}

public class Exercise_2 {
    public static void main(String[] args) {
        House house = new House();
        Team team = new Team(3);
        team.build(house);
    }
}
