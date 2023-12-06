package Week11_Lab20;

public class NaturalMergeSorter {
	public int getSortedRunLength(int[] array, int arrayLength, int startIndex) {
        // Check if startIndex is out of bounds
        if (startIndex >= arrayLength || startIndex < 0) {
            return 0;
        }

        int runLength = 1; // Start with a length of 1 as the starting element itself is a sorted run

        // Iterate through the array starting from startIndex
        for (int i = startIndex; i < arrayLength - 1; i++) {
            // Check if the current element is less than or equal to the next element
            if (array[i] <= array[i + 1]) {
                runLength++;
            } else {
                break; // End of sorted run
            }
        }

        return runLength;
    }

	public void naturalMergeSort(int[] array, int arrayLength) {
        // Check if the array is trivially sorted or invalid
        if (arrayLength < 2) {
            return;
        }

        // Continue sorting until the entire array is one sorted run
        boolean sorted = false;
        while (!sorted) {
            int startIndex = 0;
            sorted = true; // Assume the array is sorted, will be set to false if we find multiple runs

            while (startIndex < arrayLength) {
                // Find the length of the first sorted run
                int firstRunLength = getSortedRunLength(array, arrayLength, startIndex);
                int secondRunStart = startIndex + firstRunLength;

                // If second run start is beyond array end, the first run is the last run
                if (secondRunStart >= arrayLength) {
                    break;
                }

                // Find the length of the second sorted run
                int secondRunLength = getSortedRunLength(array, arrayLength, secondRunStart);
                int secondRunEnd = secondRunStart + secondRunLength - 1;

                // Merge the two runs
                merge(array, startIndex, secondRunStart - 1, secondRunEnd);

                // Update startIndex for next iteration
                startIndex = secondRunEnd + 1;

                // If there were at least two runs, the array wasn't fully sorted
                sorted = sorted && (secondRunLength == arrayLength - secondRunStart);
            }
        }
    }
	
	public void merge(int[] numbers, int leftFirst, int leftLast, 
	   int rightLast) {
		int mergedSize = rightLast - leftFirst + 1;
		int[] mergedNumbers = new int[mergedSize];
		int mergePos = 0;
		int leftPos = leftFirst;
		int rightPos = leftLast + 1;
      
      // Add smallest element from left or right partition to merged numbers
		while (leftPos <= leftLast && rightPos <= rightLast) {
			if (numbers[leftPos] <= numbers[rightPos]) {
				mergedNumbers[mergePos] = numbers[leftPos];
				leftPos++;
			}
			else {
				mergedNumbers[mergePos] = numbers[rightPos];
				rightPos++;
			}
			mergePos++;
		}
      
      // If left partition isn't empty, add remaining elements to mergedNumbers
		while (leftPos <= leftLast) {
			mergedNumbers[mergePos] = numbers[leftPos];
			leftPos++;
			mergePos++;
		}
      
      // If right partition isn't empty, add remaining elements to mergedNumbers
		while (rightPos <= rightLast) {
			mergedNumbers[mergePos] = numbers[rightPos];
			rightPos++;
			mergePos++;
		}
      
      // Copy merged numbers back to numbers
		for (mergePos = 0; mergePos < mergedSize; mergePos++) {
			numbers[leftFirst + mergePos] = mergedNumbers[mergePos];
		}
      
      // Free temporary array
		mergedNumbers = null;
	}
}