public class Decryption {
    /**
     * This method Decrypts the Encrypted Array of numbers
     */

    public Exception KeysNotEqualException = new Exception("Invalid Amount of Order and Keys!");
    public Exception MethodNotFoundException = new Exception("Encryption method not found, Please give Correct order!");
    private float chars[];
    private int length;
    private boolean isEvenLength;

    public Decryption(float[] encrypted_chars) {
        this.chars = encrypted_chars;
        this.length = this.chars.length;
        this.isEvenLength = this.length % 2 == 0;
    }


    // This methods decrypts the chars array from add method.
    public void add(int key) {
        for(int i = 0; i < this.length; i++)
            this.chars[i] = this.chars[i] - key;
    }


    // Decryption method for addNextNum method
    public void addNextNum(int key) {
        if(this.length < 2) {
            this.chars[0] -= key;
        } else if(this.length == 2) {
            this.chars[1] = this.chars[1] - this.chars[0] - key;
        } else {
            this.chars[this.length - 1] = this.chars[length - 1] - key - this.chars[1];
            for(int i = this.length - 2; i >= 0; i--)
                this.chars[i] = this.chars[i] - this.chars[i + 1] - key;   
        }
    }


    public void multiply(int key) {
        int i;
        for(i = 0; i < this.length; i++)
            this.chars[i] = this.chars[i] / key;
    }


    public void addMirrorElements(int key) {
        int len = (int) this.length / 2;


        if(this.isEvenLength) {
            subFirstFromLast(len);
        } else {
            this.chars[len] = this.chars[len] - this.chars[len + 1];
            subFirstFromLast(len + 1);
        }

        for(int i = 0; i < len; i++)
            this.chars[i] = this.chars[i] - this.chars[this.length - i - 1] - key;
    }


    public void addKeyMultipliedMirrorElement(int key) {
        int len = (int) this.length / 2;
        
        if(this.isEvenLength) {
            subFirstFromLast(len);
        } else {
            this.chars[len] = this.chars[len] - this.chars[len + 1];
            subFirstFromLast(len + 1);
        }

        for(int i = 0; i < len; i++)
            this.chars[i] = this.chars[i] - (this.chars[this.length - i - 1] * key);
    }


    public void addKeyMultipliedElement(int key) {
        int len = (int) this.length / 2;
        
        if(this.isEvenLength) {
            subFirstFromLast(len);
        } else {
            this.chars[len] = this.chars[len] - this.chars[len + 1];
            subFirstFromLast(len + 1);
        }

        for(int i = 0; i < len; i++)
            this.chars[i] = (this.chars[i] - this.chars[this.length - i - 1]) / key;
    }


    public void addKeyMultipliedElementMirror(int key) {
        int len = (int) this.length / 2;
        
        if(this.isEvenLength) {
            subFirstFromLast(len);
        } else {
            this.chars[len] = this.chars[len] - this.chars[len + 1];
            subFirstFromLast(len + 1);
        }

        for(int i = 0; i < len; i++)
            this.chars[i] = this.chars[i] / key - this.chars[this.length - i - 1];
    }

    private void subFirstFromLast(int len) {
        for(int i = len; i < this.length; i++)
            this.chars[i] = this.chars[i] - this.chars[i - len];
    }

    // Decryption
    public void Decrypt(int[] order, int[] keys) throws Exception{
        int i, KeyLen, OrderLen;
        KeyLen = keys.length;
        OrderLen = order.length;

        if(OrderLen != KeyLen) {
            throw KeysNotEqualException;
        } else {
            for(i = OrderLen - 1; i >= 0; i--) {
                switch(order[i]) {
                    case 1:
                        this.add(keys[i]);
                        break;
                    case 2:
                        this.addNextNum(keys[i]);
                        break;
                    case 3:
                        this.multiply(keys[i]);
                        break;
                    case 4:
                        this.addMirrorElements(keys[i]);
                        break;
                    case 5:
                        this.addKeyMultipliedMirrorElement(keys[i]);
                        break;
                    case 6:
                        this.addKeyMultipliedElement(keys[i]);
                        break;
                    case 7:
                        this.addKeyMultipliedElementMirror(keys[i]);
                        break;
                    default:
                        throw MethodNotFoundException;
                }
            }
        }
    }


    // To return the chars Array
    public float[] getChars() { return this.chars; }
}