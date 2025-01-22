public class Building {
    private int energyCost;
    private int workersRequired;
    private int productionOutput;
    private int goldUpgradeCost;
    private int woodUpgradeCost;
    private int stoneUpgradeCost;
    private int level;

    public Building(int energyCost, int workersRequired, int productionOutput,
                    int goldUpgradeCost, int woodUpgradeCost, int stoneUpgradeCost, int level) {
        this.energyCost = energyCost;
        this.workersRequired = workersRequired;
        this.productionOutput = productionOutput;
        this.goldUpgradeCost = goldUpgradeCost;
        this.woodUpgradeCost = woodUpgradeCost;
        this.stoneUpgradeCost = stoneUpgradeCost;
        this.level = level;
    }

    // Getters and setters for each property, e.g.:
    public int getEnergyCost() { return energyCost; }
    public void setEnergyCost(int energyCost) { this.energyCost = energyCost; }
    public int getWorkersRequired() { return workersRequired; }
    public void setWorkersRequired(int workersRequired) { this.workersRequired = workersRequired; }
    public int getProductionOutput() { return productionOutput; }
    public void setProductionOutput(int productionOutput) { this.productionOutput = productionOutput; }
    public int getGoldUpgradeCost() { return goldUpgradeCost; }
    public void setGoldUpgradeCost(int goldUpgradeCost) { this.goldUpgradeCost = goldUpgradeCost; }
    public int getWoodUpgradeCost() { return woodUpgradeCost; }
    public void setWoodUpgradeCost(int woodUpgradeCost) { this.woodUpgradeCost = woodUpgradeCost; }
    public int getStoneUpgradeCost() { return stoneUpgradeCost; }
    public void setStoneUpgradeCost(int stoneUpgradeCost) { this.stoneUpgradeCost = stoneUpgradeCost; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
}