package Service;

import Model.Allocation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;

public class FileService {

    // Save allocation history to file
    public void saveAllocations(List<Allocation> allocations) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter("allocations.txt")
                     )) {

            for (Allocation allocation : allocations) {

                writer.write(allocation.toString());

                writer.newLine();
            }

            System.out.println(
                    "Allocation history saved to allocations.txt"
            );

        } catch (IOException e) {

            System.out.println(
                    "Error saving allocation history: "
                            + e.getMessage()
            );
        }
    }


    // View saved allocation history from file
    public void viewSavedAllocations() {

        Path path = Path.of("allocations.txt");

        try {

            if (!Files.exists(path)) {

                System.out.println(
                        "No saved allocation history found."
                );

                return;
            }

            System.out.println(
                    "\n========== SAVED ALLOCATION HISTORY =========="
            );

            Files.lines(path)
                    .forEach(System.out::println);

        } catch (IOException e) {

            System.out.println(
                    "Error reading allocation history: "
                            + e.getMessage()
            );
        }
    }
}