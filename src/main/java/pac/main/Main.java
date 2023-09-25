package pac.main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        print(new File("."), "", true);
        backup(".", "../backup");
    }

    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        if (file.isDirectory()) {
            System.out.println(file.getName());
        } else {
            System.out.println(file.getName() + " *");
        }
        File[] files = file.listFiles();
        if (files == null) return;
        int total = 0;
        for (int i = 0; i < files.length; i++) {
            total += 1;
        }
        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++) {
            subDirCounter++;
            print(files[i], indent, subDirCounter == total);
        }
    }

    public static void backup(String dir, String target) throws IOException {
        File dirFrom = new File(dir);
        File dirTo = new File(target);
        dirTo.mkdir();
        File[] files = dirFrom.listFiles();
        for (File file: files) {
            if(file.isDirectory()){
                    backup(String.format("%s/%s", dir, file.getName()), String.format("%s/%s", target, file.getName()));
            } else {
                Files.copy(Path.of(file.getCanonicalPath()),Path.of(target,file.getName()));
            }
        }
    }
}


