package xyz.mahasamut.ZipCommentAdder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        String input;
        String output;
        String comment;
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("==============================================");
        System.out.println();
        System.out.println("   Zip Comment Adder made by M4h45amu7x");
        System.out.println();
        System.out.println("==============================================");
        System.out.println();

        System.out.println("Input:");
        input = scanner.nextLine();

        System.out.println("Output:");
        output = scanner.nextLine();

        System.out.println("Comment:");
        comment = scanner.nextLine();

        scanner.close();

        System.out.println("Start copying files...");

        try {
            File inputFile = new File(input);
            Map<String, byte[]> inputFIles = new HashMap<>();
            File outputFile = new File(output);

            FileUtils.copyFile(inputFile, outputFile);

            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outputFile));
            ZipFile inputZipFile = new ZipFile(inputFile);
            Enumeration<? extends ZipEntry> inputZipEntries = inputZipFile.entries();

            while (inputZipEntries.hasMoreElements()) {
                ZipEntry zipEntry = inputZipEntries.nextElement();

                if (!zipEntry.isDirectory()) {
                    try (InputStream zipInputStream = inputZipFile.getInputStream(zipEntry)) {
                        zipOutputStream.putNextEntry(zipEntry);
                        zipOutputStream.write(IOUtils.toByteArray(zipInputStream));
                        zipOutputStream.closeEntry();
                        System.out.println("Copied " + zipEntry.getName());
                    }
                }
            }

            System.out.println("Adding comment...");
            zipOutputStream.setComment(comment);
            zipOutputStream.close();

            System.out.println();
            System.out.println("=====================================");
            System.out.println();
            System.out.println("   Added zip comment successfully!");
            System.out.println();
            System.out.println("=====================================");
            System.out.println();
        } catch (Exception e) {
            System.out.println();
            System.out.println("=====================================");
            System.out.println();
            System.out.println("   Can't add zip comment");
            System.out.println();
            System.out.println("=====================================");
            System.out.println();
            e.printStackTrace();
        }
    }

}
