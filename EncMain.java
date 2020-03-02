import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

/**
 * Created by Ameenur Rahman, Vishnu Rudhva and Denesh raj.
 * This Encryption is a set of simple methods which is used to encrypt, passing data from one method to another till end in a random manner.
 */


// This method is to validate Encryption Algorithms

public class EncMain {
    private static Scanner sc = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  // Input Reader
    public static void main(String args[]) {
        int[] order = {1, 2, 3, 4, 5, 6, 7};            // Random Order
        int[] keys = {10, 20, 10, 10, 10, 10, 10};      // Random Keys
        boolean quit = false;
        String result, test;

        System.out.printf("Do you want to test(Y or n): ");
        try {
            test = br.readLine();

            if(test.equalsIgnoreCase("y")) {
                quit = true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Test Here.
        if(quit) {
            int len = getInputArrayLength();
            int[] TestOrder = {1, 2, 3, 4};
            int[] Keys = {10, 20, 10, 10};
            float[] encChars = getInputArray(len), EncryptedChars;
            float[] DecryptedChars;

            EncryptedChars = Encrypt(encChars, TestOrder, Keys);
            DecryptedChars = DecryptFloatRet(EncryptedChars, TestOrder, Keys);
            printArray(EncryptedChars, "Encrypted Array");
            printArray(DecryptedChars, "Decrypted Array");
        }

        // If not testing.
        while(!quit) {
            System.out.println("Enter the options to perform your operation: ");
            System.out.println("\t1. Encryption");
            System.out.println("\t2. Decryption");
            System.out.println("\t3. Exit");
            System.out.printf("Enter your Option: ");
            int option = sc.nextInt();
            breakLine();
            switch(option) {
                case 1:
                    System.out.println("Enter the options for Encryption!");
                    System.out.println("\t1. To Encrypt a String");
                    System.out.println("\t2. To Encrypt a File");
                    System.out.println("\t3. Exit");
                    System.out.printf("Enter your Option: ");
                    option = sc.nextInt();
                    breakLine();
                    switch(option) {
                        case 1:
                            result = getEncryptedString(order, keys);
                            System.out.println("Encrypted String: " + result);
                            break;
                        case 2:
                            encryptFile(order, keys);
                            break;
                        case 3:
                            quit = true;
                            break;
                        default:
                            System.out.println("Please Enter the Valid Option!");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Enter the options for Decryption!");
                    System.out.println("\t1. To Decrypt a String");
                    System.out.println("\t2. To Decrypt a File");
                    System.out.println("\t3. Exit");
                    System.out.printf("Enter your Option: ");
                    option = sc.nextInt();
                    breakLine();
                    switch(option) {
                        case 1:
                            result = getDecryptedString(order, keys);
                            System.out.println("Decrypted String: " + result);
                            break;
                        case 2:
                            decryptFile(order, keys);
                            break;
                        case 3:
                            quit = true;
                            break;
                        default:
                            System.out.println("Please Enter the Valid Option!");
                            break;
                    }
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println("Please Enter the Valid Option!");
                    break;
            }
        }

        try {
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        sc.close();     //Closing the Scanner
    }

    
    // Method to break new line
    public static void breakLine() { System.out.println(); }

    // This will get the String to Encrypt
    public static String getInputString(String msg) {
        String unEncryptedString = "";
        System.out.printf("Enter the " + msg + ": ");
        try {
            unEncryptedString = br.readLine();
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println(msg + ": " + unEncryptedString);
        return unEncryptedString;
    }
    

    // Getting the Length of the Array
    public static int getInputArrayLength() {
        int length;     // Length of the Chars Array     
        System.out.printf("Enter the Length of the Array: ");
        length = sc.nextInt();
        return length;
    }


    // Gets the Input Array 
    public static float[] getInputArray(int length) {
        float[] chars = new float[length];
        // Getting the Input Array from the User
        for(int i = 0; i < length; i++) {
            System.out.printf("Array[" + (i + 1) + "]: ");
            chars[i] = (float) sc.nextInt();
        }

        return chars;
    }


    // Encrypts the String on fly as per user's request
    public static String getEncryptedString(int[] order, int[] keys) {
        String chars;
        float[] EncryptedString;

        chars = getInputString("String to Encrypt");
        EncryptedString = Encrypt(chars, order, keys);
        return convertToString(EncryptedString);
    }


    // Decrypts the String as the User Requested
    public static String getDecryptedString(int[] order, int[] keys) {
        String EncryptedString = getInputString("String to Decrypt");
        return Decrypt(EncryptedString, order, keys);
    }


    // Encrypts a String
    public static float[] Encrypt(String UnEncryptedString, int[] order, int[] keys) {
        float[] chars, CharsToEncrypt, EncryptedChars;    // To hold the Array of numbers for Encryption

        // Encryption
        chars = convertToNum(UnEncryptedString);
        CharsToEncrypt = CloneArray(chars);
        EncryptedChars = encryptedArray(CharsToEncrypt, order, keys);
        return EncryptedChars;
    }

    // Encrypts a String with float array.
    public static float[] Encrypt(float[] chars, int[] order, int[] keys) {
        float[] CharsToEncrypt, EncryptedChars;    // To hold the Array of numbers for Encryption

        // Encryption
        CharsToEncrypt = CloneArray(chars);
        EncryptedChars = encryptedArray(CharsToEncrypt, order, keys);
        return EncryptedChars;
    }

    // Decrypts the Encrypted String
    public static String Decrypt(String EncryptedString, int[] order, int[] keys) {
        float[] EncryptedChars, DecryptedChars, CharsToDecrypt;  // To hold the Array of numbers for Encryption

        EncryptedChars = convertToNum(EncryptedString);
        CharsToDecrypt = CloneArray(EncryptedChars);
        DecryptedChars = decryptedArray(CharsToDecrypt, order, keys);
        return convertToString(DecryptedChars);
    }

    // Overriding for the Decryption for which float array is given.
    public static String Decrypt(float[] EncryptedChars, int[] order, int[] keys) {
        float[] DecryptedChars, CharsToDecrypt;  // To hold the Array of numbers for Encryption

        CharsToDecrypt = CloneArray(EncryptedChars);
        DecryptedChars = decryptedArray(CharsToDecrypt, order, keys);
        return convertToString(DecryptedChars);
    }

    // Overriding for the Decryption for which float array is given.
    public static float[] DecryptFloatRet(float[] EncryptedChars, int[] order, int[] keys) {
        float[] CharsToDecrypt;  // To hold the Array of numbers for Encryption

        CharsToDecrypt = CloneArray(EncryptedChars);
        return decryptedArray(CharsToDecrypt, order, keys);
    }

    // Checking Each and Every Methods in Encryption Class
    public static float[] encryptedArray(float[] chars, int[] order, int[] keys) {
        Encryption enc = new Encryption(chars);
        try {
            enc.Encrypt(order, keys);
        } catch(Exception e) {
            System.out.println("Error while Encrypting: " + e.getMessage());
            System.exit(0);
        }

        float[] EncChars = enc.getChars();
        return EncChars;
    }

    // Checking Each and Every Methods in Decryption Class
    public static float[] decryptedArray(float[] chars, int[] order, int[] keys) {
        Decryption dec = new Decryption(chars);
        try {
            dec.Decrypt(order, keys);
        } catch(Exception e) {
            System.out.println("Error while Decrypting: " + e.getMessage());
            System.exit(0);
        }
        float[] DecChars = dec.getChars();
        return DecChars;
    }

    // Printing the Result of the Array
    public static void printArray(float[] chars, String msg) {
        int len = chars.length;
        System.out.printf(msg + ": ");
        for(int i = 0; i < len - 1; i++) {
            System.out.printf(chars[i] + " ");
        }
        System.out.println(chars[len - 1]);
    }


    // Printing the Result of the Array Without msg
    public static void printArray(float[] chars) {
        int len = chars.length;
        for(int i = 0; i < len - 1; i++) {
            System.out.printf(chars[i] + " ");
        }
        System.out.println(chars[len - 1]);
    }


    // Clone the Given Array
    public static float[] CloneArray(float[] array) {
        int len = array.length;
        float[] ClonedArray = new float[len];

        for(int i = 0; i < len; i++) { ClonedArray[i] = array[i]; }

        return ClonedArray;
    }


    // Convert Given String to array
    public static float[] convertToNum(String Text) {
        int len = Text.length(), AsciiChar;
        float[] ReturnArray = new float[len];

        for(int i = 0; i < len; i++) {
            AsciiChar = Text.charAt(i);
            ReturnArray[i] = (float) AsciiChar;
        }

        return ReturnArray;
    }


    // Convert Given array to String
    public static String convertToString(float[] DecryptedArray) {
        int len = DecryptedArray.length;
        String ReturnString = "";
        char c;
        int num;

        for(int i = 0; i < len; i++) {
            num = (int) DecryptedArray[i];
            c = (char) num;
            ReturnString += c;
        }

        return ReturnString;
    }
    // Encrypts the File
    public static void encryptFile(int[] order, int[] keys) {
        String line = null;
        boolean flag = false;
        System.out.print("Enter the Name of the File to Encrypt: ");
        try {
            String FileName = br.readLine();
            String EncryptedString = "";
            String buffer = "";
            File file = new File(FileName);
            Scanner FileScan = new Scanner(file);

            FileScan.useDelimiter(" ");
            while(FileScan.hasNext()) {
                line = FileScan.next();
                if(buffer.length() != 0) {
                    line = buffer + line;
                    buffer = "";
                } else {
                    if(line.length() == 1) {
                        buffer = line + " ";
                        continue;
                    }
                }

                float[] EncStr = Encrypt(line, order, keys);
                if(!flag) {
                    EncryptedString = EncryptedString + Arrays.toString(EncStr);
                    flag = true;
                } else {
                    EncryptedString = EncryptedString + "\n" + Arrays.toString(EncStr);
                }

                System.out.println("\t" + EncStr);
            }

            BufferedWriter out = new BufferedWriter(new FileWriter(FileName));
            out.write(EncryptedString + "\n");

            System.out.println("File Encrypted Successfully!");
            breakLine();

            out.close();
            FileScan.close();
        } catch(FileNotFoundException e) {
            System.out.println("Please Enter the Correct File Name! " + e.getMessage());
        } catch(IOException e) {
            System.out.println("Error in Reading the File! " + e.getMessage());
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    // Decrypts the File
    public static void decryptFile(int[] order, int[] keys) {
        System.out.print("Enter the Name of the Encrypted File to Decrypt: ");
        String line = null;
        try {
            String FileName = br.readLine();
            String DecryptedString = "";
            File file = new File(FileName);
            Scanner FileScan = new Scanner(new FileReader(file));

            while(FileScan.hasNext()) {
                line = FileScan.nextLine();
                float[] parsedLine = getArrayFromString(line);
                String DecStr = Decrypt(parsedLine, order, keys);
                DecryptedString += DecStr + " ";
            }


            System.out.printf("Enter the Output File to write to: ");
            String file2 = br.readLine();
            File File2 = new File(file2);

            BufferedWriter out = new BufferedWriter(new FileWriter(File2));
            out.write(DecryptedString);
            
            System.out.println("File Decrypted Successfully!");
            breakLine();

            out.close();
            FileScan.close();
        } catch(FileNotFoundException e) {
            System.out.println("Please Enter the Correct File Name! " + e.getMessage());
        } catch(IOException e) {
            System.out.println("Error in Reading the File! " + e.getMessage());
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Converts the Arrays.toString() output to an Array.
    private static float[] getArrayFromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        float result[] = new float[strings.length];
        
        for (int i = 0; i < result.length; i++) {
            result[i] = Float.parseFloat(strings[i]);
        }

        return result;
    }
}