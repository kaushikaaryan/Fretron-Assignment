import java.util.*;

public class AllocateApples {

    public static void main(String[] args) {
        int[] weights = {400, 100, 400, 300, 200, 300, 100, 200};
        int[] payments = {50, 30, 20}; // Ram, Shyam, Rahim

        List<List<Integer>> allocations = distributeApples(weights, payments);

       
        int totalWeight = Arrays.stream(weights).sum();
        String[] names = {"Ram", "Shyam", "Rahim"};
        for (int i = 0; i < allocations.size(); i++) {
            List<Integer> allocatedWeights = allocations.get(i);
            int personTotalWeight = allocatedWeights.stream().mapToInt(Integer::intValue).sum();
            System.out.println(names[i] + " : " + allocatedWeights + " (total " + personTotalWeight + "g, " + (personTotalWeight / (double) totalWeight) * 100 + "%)");
        }
    }

    public static List<List<Integer>> distributeApples(int[] weights, int[] payments) {
        int totalPayment = Arrays.stream(payments).sum();
        double[] proportions = new double[payments.length];
        for (int i = 0; i < payments.length; i++) {
            proportions[i] = payments[i] / (double) totalPayment;
        }

        Integer[] weightObjects = Arrays.stream(weights).boxed().toArray(Integer[]::new);
        Arrays.sort(weightObjects, Collections.reverseOrder());

        List<List<Integer>> allocations = new ArrayList<>();
        int[] currentTotals = new int[payments.length];
        for (int i = 0; i < payments.length; i++) {
            allocations.add(new ArrayList<>());
        }

        int totalAllocatedWeight = 0;

        for (int weight : weightObjects) {
            // Find the person whose current proportion is furthest from their ideal proportion
            double maxDiff = -1;
            int chosenPerson = -1;
            for (int i = 0; i < payments.length; i++) {
                double currentProp = (totalAllocatedWeight == 0) ? 0 : currentTotals[i] / (double) totalAllocatedWeight;
                double idealProp = proportions[i];
                double diff = idealProp - currentProp;
                if (diff > maxDiff) {
                    maxDiff = diff;
                    chosenPerson = i;
                }
            }

            // Allocate the apple to that person
            allocations.get(chosenPerson).add(weight);
            currentTotals[chosenPerson] += weight;
            totalAllocatedWeight += weight;
        }

        return allocations;
    }
}
