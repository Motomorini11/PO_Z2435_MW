import java.util.prefs.Preferences;

public class Game {
    private int turn;
    private int energy;
    private int maxEnergy;
    private int wood;
    private int stone;
    private int food;
    private int gold;
    private int iron;
    private int sword;
    private int tool;
    private int workers;
    private int maxWorkers;
    private int FreeWorkers;

    private Preferences prefs;
    private final String RECORD_KEY = "highestTurnRecord";

    public Game() {
        prefs = Preferences.userNodeForPackage(Game.class);
        this.turn = 1;
        this.energy = 10;  // Starting energy
        this.maxEnergy = energy;
        this.wood = 0;
        this.stone = 0;
        this.food = 400; // Starting food
        this.gold = 50; // Starting gold
        this.iron = 0;
        this.sword = 21;
        this.tool = 0;
        this.workers = 5; // Starting workers
        this.maxWorkers = 5; // Initial capacity
        this.FreeWorkers = workers;
    }

    // End the current turn, consume food, regenerate energy, etc.
    public void nextTurn() {
        turn++;

        energy = Math.min(energy + 10, maxEnergy);

        // Handle food consumption by workers
        if (food >= workers) {
            food -= workers;  // Workers consume food as usual
        } else {
            int starvingWorkers = workers - food;  // Workers that can't be fed
            food = 0;  // All food is consumed
            workers -= starvingWorkers;  // Starving workers die
        }

        // Ensure that no resource goes below 0
        wood = Math.max(wood, 0);
        stone = Math.max(stone, 0);
        food = Math.max(food, 0);
        workers = Math.max(workers, 0);  // Game over if workers hit 0

        // Reset worker usage
        FreeWorkers = workers;

        // Replenish energy logic and other turn-end logic
        energy = Math.min(energy + 10, maxEnergy);
        checkForGameOver();
    }

    public boolean checkForGameOver() {
        return workers <= 0;
    }

    public int getTurn() {
        return turn;
    }

    public int getEnergy() {
        return energy;
    }

    public int getWood() {
        return wood;
    }
    public int getIron() {
        return iron;
    }
    public int getSword() {
        return sword;
    }
    public int getTool() {
        return tool;
    }

    public int getStone() {
        return stone;
    }

    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public int getWorkers() {
        return workers;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public int getFreeWorkers() {
        return FreeWorkers;
    }

    public void storeHighestTurn() {
        int currentRecord = prefs.getInt(RECORD_KEY, 0);  // Retrieve the stored record
        if (turn > currentRecord) {
            prefs.putInt(RECORD_KEY, turn);  // Store new record
        }
    }

    public int getHighestTurn() {
        return prefs.getInt(RECORD_KEY, 0);  // Return the highest stored turn
    }

    public void useSword(int amount) {
        sword -= amount;
    }

    public boolean deductEnergy(int amount) {
        if (energy >= amount) {
            energy -= amount;
            return true; // Deduction successful
        } else {
            return false; // Insufficient energy
        }
    }

    public boolean deductWorkers(int amount) {
        if (FreeWorkers >= amount) {
            FreeWorkers -= amount;
            return true; // Deduction successful
        } else {
            return false; // Insufficient workers
        }
    }

    public boolean deductGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            return true;
        }else {
            return false;
        }

    }

    public void addEnergy(int amount) {
        energy = Math.min(energy + amount, maxEnergy);
    }

    public void addWorkers(int amount) {
        FreeWorkers = Math.min(FreeWorkers + amount, maxWorkers);
    }
}