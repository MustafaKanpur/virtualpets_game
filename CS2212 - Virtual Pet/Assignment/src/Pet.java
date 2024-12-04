import java.util.Random;

/**
 * The class to represent a virtual pet with attributes and behaviors
 * @author Mustafa Kanpurwala
 * @author Teagan Martins
 */
public class Pet {

    // Attributes related to the pet's state
    private int health;        // Current health of the pet
    private int sleep;         // Current sleep level of the pet
    private int fullness;      // Current fullness level of the pet
    private int happiness;     // Current happiness level of the pet
    private int score;         // Current score of the pet

    // Maximum possible values for attributes
    private int maxHealth;     // Maximum health value the pet can have
    private int maxSleep;      // Maximum sleep value the pet can have
    private int maxFullness;   // Maximum fullness value the pet can have
    private int maxHappiness;  // Maximum happiness value the pet can have

    // Attribute loss rates over time
    private int healthLoss;    // Rate at which health decreases over time
    private int sleepLoss;     // Rate at which sleep decreases over time
    private int fullnessLoss;  // Rate at which fullness decreases over time
    private int happinessLoss; // Rate at which happiness decreases over time

    // Attribute gain rates from activities or items
    public int happinessGain;  // Amount of happiness gained from certain actions or items
    public int sleepGain;      // Amount of sleep gained from certain actions or items
    public int fullnessGain;   // Amount of fullness gained from certain actions or items
    public int healthGain;     // Amount of health gained from certain actions or items

    // Pet states
    private boolean dead;       // Indicates if the pet is dead
    private boolean sleeping;   // Indicates if the pet is currently sleeping
    private boolean hungry;     // Indicates if the pet is currently hungry
    private boolean angry;      // Indicates if the pet is currently angry

    // Inventory counts for items
    private int bananaCount;    // Number of bananas in the inventory
    private int pieCount;       // Number of pies in the inventory
    private int pizzaCount;     // Number of pizzas in the inventory
    private int ballCount;      // Number of balls in the inventory
    private int plushCount;     // Number of plush toys in the inventory
    private int swordCount;     // Number of swords in the inventory

    // Other attributes
    private String difficulty;  // The game's difficulty level
    private String name;        // Name of the pet
    private int nextThreshold;  // The score threshold for earning rewards

    private Random random;      // Random object for generating random numbers
    private int randomNumber;   // Stores the last generated random number

    /**
     * Constructor for the Pet class. Initializes a virtual pet with specified attributes, 
     * inventory counts, loss and gain rates, and game difficulty level.
     * 
     * @param difficulty     The difficulty level of the game.
     * @param healthVal      Initial health value.
     * @param sleepVal       Initial sleep value.
     * @param fullnessVal    Initial fullness value.
     * @param happinessVal   Initial happiness value.
     * @param maxHealth      Maximum health value.
     * @param maxSleep       Maximum sleep value.
     * @param maxFullness    Maximum fullness value.
     * @param maxHappiness   Maximum happiness value.
     * @param healthLoss     Rate of health decrease over time.
     * @param sleepLoss      Rate of sleep decrease over time.
     * @param fullnessLoss   Rate of fullness decrease over time.
     * @param happinessLoss  Rate of happiness decrease over time.
     * @param healthGain     Amount of health gained from actions or items.
     * @param sleepGain      Amount of sleep gained from actions or items.
     * @param fullnessGain   Amount of fullness gained from actions or items.
     * @param happinessGain  Amount of happiness gained from actions or items.
     * @param pieCount       Initial number of pies in the inventory.
     * @param bananaCount    Initial number of bananas in the inventory.
     * @param pizzaCount     Initial number of pizzas in the inventory.
     * @param ballCount      Initial number of balls in the inventory.
     * @param plushCount     Initial number of plush toys in the inventory.
     * @param swordCount     Initial number of swords in the inventory.
     * @param name           The name of the pet.
     * @param score          The starting score for the pet.
     */
    public Pet(String difficulty, int healthVal, int sleepVal, int fullnessVal, int happinessVal, 
            int maxHealth, int maxSleep, int maxFullness, int maxHappiness, 
            int healthLoss, int sleepLoss, int fullnessLoss, int happinessLoss, 
            int healthGain, int sleepGain, int fullnessGain, int happinessGain, 
            int pieCount, int bananaCount, int pizzaCount, int ballCount, 
            int plushCount, int swordCount, String name, int score) {

     // Initialize basic attributes
     this.difficulty = difficulty;
     this.health = healthVal;
     this.sleep = sleepVal;
     this.fullness = fullnessVal;
     this.happiness = happinessVal;
     this.score = score;

     // Initialize maximum values
     this.maxHealth = maxHealth;
     this.maxSleep = maxSleep;
     this.maxFullness = maxFullness;
     this.maxHappiness = maxHappiness;

     // Initialize loss rates
     this.healthLoss = healthLoss;
     this.sleepLoss = sleepLoss;
     this.fullnessLoss = fullnessLoss;
     this.happinessLoss = happinessLoss;

     // Initialize gain rates
     this.healthGain = healthGain;
     this.sleepGain = sleepGain;
     this.fullnessGain = fullnessGain;
     this.happinessGain = happinessGain;

     // Initialize inventory counts
     this.pieCount = pieCount;
     this.bananaCount = bananaCount;
     this.pizzaCount = pizzaCount;
     this.ballCount = ballCount;
     this.plushCount = plushCount;
     this.swordCount = swordCount;

     // Initialize other attributes
     this.name = name;
     this.nextThreshold = 50; // Set the initial threshold for rewards
     this.dead = false;
     this.sleeping = false;
     this.hungry = false;
     this.angry = false;

     // Random object for generating random numbers
     this.random = new Random();
 }


    /**
     * Gets the current score of the pet.
     * @return The current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Increases the score by a specified amount and checks for rewards at score thresholds.
     * 
     * @param amount The amount to increase the score by.
     */
    public void increaseScore(int amount) {
        this.random = new Random();
        randomNumber = random.nextInt(7); // Generate a random number between 0 and 6

        this.score += amount; // Add the specified amount to the score

        // Check if the score has surpassed the next threshold for rewards
        while (this.score >= nextThreshold) {
            // Award a random item based on the generated random number
            switch (randomNumber) {
                case 0: this.pieCount++; break;  // Add a pie to the inventory
                case 1: this.bananaCount++; break;  // Add a banana to the inventory
                case 2: this.pizzaCount++; break;  // Add a pizza to the inventory
                case 3: this.ballCount++; break;  // Add a ball to the inventory
                case 4: this.swordCount++; break;  // Add a sword to the inventory
                case 5: this.plushCount++; break;  // Add a plush toy to the inventory
                default: break;  // No reward for other cases
            }
            nextThreshold += 50; // Set the next threshold for rewards
        }
    }

    /**
     * Decreases the score by a specified amount.
     * 
     * @param amount The amount to decrease the score by.
     */
    public void decreaseScore(int amount) {
        this.score -= amount; // Subtract the specified amount from the score
    }

    /**
     * Gets the current health of the pet.
     * @return The current health value.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Sets a new health value for the pet, ensuring it does not exceed the maximum health.
     * @param newHealth The new health value to set.
     */
    public void setHealth(int newHealth) {
        this.health = Math.min(newHealth, this.maxHealth);
    }

    /**
     * Gets the current sleep level of the pet.
     * @return The current sleep value.
     */
    public int getSleep() {
        return this.sleep;
    }

    /**
     * Sets a new sleep value for the pet, ensuring it does not exceed the maximum sleep.
     * @param newSleep The new sleep value to set.
     */
    public void setSleep(int newSleep) {
        this.sleep = Math.min(newSleep, this.maxSleep);
    }

    /**
     * Gets the current fullness level of the pet.
     * @return The current fullness value.
     */
    public int getFullness() {
        return this.fullness;
    }

    /**
     * Sets a new fullness value for the pet, ensuring it does not exceed the maximum fullness.
     * @param newFull The new fullness value to set.
     */
    public void setFullness(int newFull) {
        this.fullness = Math.min(newFull, this.maxFullness);
    }

    /**
     * Gets the current happiness level of the pet.
     * @return The current happiness value.
     */
    public int getHappiness() {
        return this.happiness;
    }

    /**
     * Sets a new happiness value for the pet, ensuring it does not exceed the maximum happiness.
     * @param newHappy The new happiness value to set.
     */
    public void setHappiness(int newHappy) {
        this.happiness = Math.min(newHappy, this.maxHappiness);
    }

    /**
     * Gets the current health loss rate.
     * @return The health loss rate.
     */
    public int getHealthLoss() {
        return this.healthLoss;
    }

    /**
     * Gets the current sleep loss rate.
     * @return The sleep loss rate.
     */
    public int getSleepLoss() {
        return this.sleepLoss;
    }

    /**
     * Gets the current fullness loss rate.
     * @return The fullness loss rate.
     */
    public int getFullnessLoss() {
        return this.fullnessLoss;
    }

    /**
     * Gets the current happiness loss rate.
     * @return The happiness loss rate.
     */
    public int getHappinessLoss() {
        return this.happinessLoss;
    }

    /**
     * Gets the health gain amount from actions or items.
     * @return The health gain amount.
     */
    public int getHealthGain() {
        return this.healthGain;
    }

    /**
     * Gets the sleep gain amount from actions or items.
     * @return The sleep gain amount.
     */
    public int getSleepGain() {
        return this.sleepGain;
    }

    /**
     * Gets the fullness gain amount from actions or items.
     * @return The fullness gain amount.
     */
    public int getFullnessGain() {
        return this.fullnessGain;
    }

    /**
     * Gets the happiness gain amount from actions or items.
     * @return The happiness gain amount.
     */
    public int getHappinessGain() {
        return this.happinessGain;
    }

    /**
     * Gets the maximum health value for the pet.
     * @return The maximum health value.
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Gets the maximum fullness value for the pet.
     * @return The maximum fullness value.
     */
    public int getMaxFullness() {
        return this.maxFullness;
    }

    /**
     * Gets the maximum sleep value for the pet.
     * @return The maximum sleep value.
     */
    public int getMaxSleep() {
        return this.maxSleep;
    }

    /**
     * Gets the maximum happiness value for the pet.
     * @return The maximum happiness value.
     */
    public int getMaxHappiness() {
        return this.maxHappiness;
    }

    /**
     * Checks if the pet is dead.
     * @return True if the pet is dead, false otherwise.
     */
    public boolean isDead() {
        return this.dead;
    }

    /**
     * Sets the pet's dead state.
     * @param deadVal The new dead state to set.
     */
    public void setDead(boolean deadVal) {
        this.dead = deadVal;
    }

    /**
     * Checks if the pet is currently sleeping.
     * @return True if the pet is sleeping, false otherwise.
     */
    public boolean isSleeping() {
        return this.sleeping;
    }

    /**
     * Sets the pet's sleeping state.
     * @param sleepVal The new sleeping state to set.
     */
    public void setSleeping(boolean sleepVal) {
        this.sleeping = sleepVal;
    }

    /**
     * Checks if the pet is currently hungry.
     * @return True if the pet is hungry, false otherwise.
     */
    public boolean isHungry() {
        return this.hungry;
    }

    /**
     * Sets the pet's hungry state.
     * @param hungryVal The new hungry state to set.
     */
    public void setHungry(boolean hungryVal) {
        this.hungry = hungryVal;
    }

    /**
     * Checks if the pet is currently angry.
     * @return True if the pet is angry, false otherwise.
     */
    public boolean isAngry() {
        return this.angry;
    }

    /**
     * Sets the pet's angry state.
     * @param angryVal The new angry state to set.
     */
    public void setAngry(boolean angryVal) {
        this.angry = angryVal;
    }

    /**
     * Gets the current count of pies in the inventory.
     * @return The current pie count.
     */
    public int getPieCount() {
        return this.pieCount;
    }

    /**
     * Decreases the count of pies in the inventory by one.
     */
    public void decrementPieCount() {
        this.pieCount--;
    }

    /**
     * Gets the current count of bananas in the inventory.
     * @return The current banana count.
     */
    public int getBananaCount() {
        return this.bananaCount;
    }

    /**
     * Decreases the count of bananas in the inventory by one.
     */
    public void decrementBananaCount() {
        this.bananaCount--;
    }

    /**
     * Gets the current count of pizzas in the inventory.
     * @return The current pizza count.
     */
    public int getPizzaCount() {
        return this.pizzaCount;
    }

    /**
     * Decreases the count of pizzas in the inventory by one.
     */
    public void decrementPizzaCount() {
        this.pizzaCount--;
    }

    /**
     * Gets the current count of balls in the inventory.
     * @return The current ball count.
     */
    public int getBallCount() {
        return this.ballCount;
    }

    /**
     * Decreases the count of balls in the inventory by one.
     */
    public void decrementBallCount() {
        this.ballCount--;
    }

    /**
     * Gets the current count of plush toys in the inventory.
     * @return The current plush toy count.
     */
    public int getPlushCount() {
        return this.plushCount;
    }

    /**
     * Decreases the count of plush toys in the inventory by one.
     */
    public void decrementPlushCount() {
        this.plushCount--;
    }

    /**
     * Gets the current count of swords in the inventory.
     * @return The current sword count.
     */
    public int getSwordCount() {
        return this.swordCount;
    }

    /**
     * Decreases the count of swords in the inventory by one.
     */
    public void decrementSwordCount() {
        this.swordCount--;
    }

    /**
     * Gets the game's difficulty level.
     * @return The difficulty level.
     */
    public String getDifficulty() {
        return this.difficulty;
    }

    /**
     * Gets the pet's name.
     * @return The pet's name.
     */
    public String getName() {
        return this.name;
    }
}
