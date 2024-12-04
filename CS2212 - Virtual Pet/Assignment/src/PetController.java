/**
 * This class represents a controller for the pet class, sets up backend functionality for pet commands updating stats as is appropriate
 * @author Mustafa Kanpurwala
 */
public class PetController {
    /**
     * The pet that everything will be done to
     */
    private Pet currentPet;

    

    
    /**
     * Constructor that initializes a pet for the actions to be done to
     * @param pet
     */
    public PetController(Pet pet){
        currentPet = pet;
    }

    /**
     * Helper method that increases the pets stats for the play command, ensures that the stat doesn't go over it's max value, only increases score if the stat is below it's max value
     */
    public void playWithPet(){
        currentPet.setHappiness(currentPet.getHappiness() + currentPet.getHappinessGain());
        if(currentPet.getHappiness() < currentPet.getMaxHappiness()){
            currentPet.increaseScore(20);
        }
        
    }

    /**
     * Helper method that increases and decreases the pets stats for the exercise command, ensures that the stat doesn't go over it's max value, only increases score if the stat is below it's max value
     */
    public void exerciseWithPet(){
        currentPet.setHealth(currentPet.getHealth() + currentPet.getHealthGain());
        currentPet.setSleep(currentPet.getSleep() - currentPet.getSleepLoss());
        currentPet.setFullness(currentPet.getFullness() - currentPet.getFullnessLoss());
        currentPet.increaseScore(20);

    
    }

    /**
     * Helper method that increases the pets stats for the vet command, decreases score when the player uses this command
     */
    public void petToVet(){
        currentPet.setHealth(currentPet.getHealth() + currentPet.getHealthGain());

        currentPet.decreaseScore(5);
    }

    /**
     * Helper method that increases the pets stats for the sleep command, ensures that the stat doesn't go over it's max value, only increases score if the stat is below it's max value
     */

    public void petSleep(){
        currentPet.setSleep(currentPet.getSleep() + currentPet.getSleepGain());

        if(currentPet.getSleep() < currentPet.getMaxSleep()){
            currentPet.increaseScore(15);

        }
    }

    /**
     * Helper method that increases the pets stats for the feed command, ensures that the stat doesn't go over it's max value, only increases score if the stat is below it's max value
     */

    public void feedPet(){
        currentPet.setFullness(currentPet.getFullness() + currentPet.getFullnessGain());

        if(currentPet.getFullness() < currentPet.getMaxFullness()){
            currentPet.increaseScore(15);

        }

        if(currentPet.isHungry() && currentPet.getFullness() > 0){
            currentPet.setHungry(false);
        }
    }

    /**
     * Helper method that increases the pets stats for the play command, ensures that the stat doesn't go over it's max value, only increases score if the stat is below it's max value
     */

    public void giftPet(){
        // same as above
        currentPet.setHappiness(currentPet.getHappiness() + currentPet.getHappinessGain());

        if(currentPet.getHappiness() < currentPet.getMaxHappiness()){
            currentPet.increaseScore(20);

        }

        if(currentPet.isAngry() && currentPet.getHappiness()  > currentPet.getMaxHappiness()/2){
            currentPet.setAngry(false);
        }
    }
    /**
     * Helper method that checks if the pet is in the sleep state, and sets the pet's state to reflect that
     */
    public void checkSleep(){
        if(currentPet.getSleep() == 0){
            currentPet.setSleeping(true);
        }
    }

    /**
     * Helper method that checks if the pet is in the dead state, and sets the pet's state to reflect that
     */
    public void checkDead(){
            if(currentPet.getHealth() == 0){
                currentPet.setDead(true);

            }
        
    }

    /**
     * Helper method that checks if the pet is in the angry state, and sets the pet's state to reflect that
     */
    public void checkAngry(){
        if(currentPet.getHappiness() == 0){
            currentPet.setAngry(true);

        }
    }

    /**
     * Helper method that checks if the pet is in the hungry state, and sets the pet's state to reflect that
     */
    public void checkHungry(){
        if(currentPet.getFullness() == 0){
            currentPet.setHungry(true);
        }
    }
}

