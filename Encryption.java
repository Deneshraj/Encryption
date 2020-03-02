public class Encryption {
    /**
     * This method Encrypts the Array of floating point numbers
     * The methods should follow the following rules:
     *      1. The methods should be Non-Commutative
     *      2. The methods should be Non-Linear.
     *      3. The methods must not cause overflow of the Number than max float value.
     */
    private float chars[];
    public Exception KeysNotEqualException = new Exception("Invalid Amount of Order and Keys!");
    public Exception MethodNotFoundException = new Exception("Encryption method not found, Please give Correct order!");
    private int length;
    private boolean isEvenLength;

    public Encryption(float ascii_chars[]) {
        this.chars = ascii_chars;
        this.length = this.chars.length;
        this.isEvenLength = this.length % 2 == 0;
    }


    // This method adds the key to chars array.
    public void add(int key) {
        for(int i = 0; i < this.length; i++)
            this.chars[i] = this.chars[i] + key;
    }


    // This method adds the Current Element and Next Element Along with the key given.
    public void addNextNum(int key) {
        if(this.length < 2) {
            this.chars[0] += key;
        } else if(this.length == 2) {
            this.chars[1] = this.chars[1] + this.chars[0] + key;
        } else {
            for(int i = 0; i < this.length - 1; i++)
                this.chars[i] += this.chars[i + 1] + key;
            this.chars[length - 1] = this.chars[length - 1] + key + this.chars[1];
        }
    }


    // This method multiplies the key to each and every number
    public void multiply(int key) {
        for(int i = 0; i < this.length; i++)
            this.chars[i] = this.chars[i] * key;
    }


    // This method adds element and element from last.
    public void addMirrorElements(int key) {
        int len = (int) this.length / 2;
        for(int i = 0; i < len; i++)
            this.chars[i] = this.chars[i] + this.chars[this.length - i - 1] + key;

        if(this.isEvenLength) addFirstToLast(len);
        else {
            addFirstToLast(len + 1);
            this.chars[len] = this.chars[len] + this.chars[len + 1];
        }

    }


    // This Method Multiplies the key with mirror element and adds the resultant to the element. 
    public void addKeyMultipliedMirrorElement(int key) {
        int len = (int) this.length / 2;
        for(int i = 0; i < len; i++)
            this.chars[i] = this.chars[i] + (this.chars[this.length - i - 1] * key);
        
        if(this.isEvenLength) addFirstToLast(len);
        else {
            addFirstToLast(len + 1);
            this.chars[len] = this.chars[len] + this.chars[len + 1];
        }
    }


    // This multiplies the key with the element and the resultant is added with mirror element and stored as element
    public void addKeyMultipliedElement(int key) {
        int len = (int) this.length / 2;
        for(int i = 0; i < len; i++)
            this.chars[i] = this.chars[i] * key + this.chars[this.length - i - 1];
        
        if(this.isEvenLength) addFirstToLast(len);
        else {
            addFirstToLast(len + 1);
            this.chars[len] = this.chars[len] + this.chars[len + 1];
        }    
    }


    // Multiply key to the Mirror added element
    public void addKeyMultipliedElementMirror(int key) {
        int len = (int) this.length / 2;
        for(int i = 0; i < len; i++)
            this.chars[i] = key * (this.chars[i] + this.chars[this.length - i - 1]);

        if(this.isEvenLength) addFirstToLast(len);
        else {
            addFirstToLast(len + 1);
            this.chars[len] = this.chars[len] + this.chars[len + 1];
        }

    }


    // Adds the first half of the elements to the resultant last halves and stored in chars
    private void addFirstToLast(int len) {
        for(int i = len; i < this.length; i++)
            this.chars[i] = this.chars[i] + this.chars[i - len];
    }


    // Encrypts the array as per the given order.
    public void Encrypt(int[] order, int[] keys) throws Exception {
        int i, KeyLen, OrderLen;
        KeyLen = keys.length;
        OrderLen = order.length;

        if(OrderLen != KeyLen) {
            throw KeysNotEqualException;
        } else {
            for(i = 0; i < OrderLen; i++) {
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