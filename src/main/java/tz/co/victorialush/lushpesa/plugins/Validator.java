package tz.co.victorialush.lushpesa.plugins;

public final class Validator {
    /**
     * Check if a provided value is empty
     * @param val String
     * @return String
     */
    public static String checkEmptiness(String val){
        //If the provided value has nothing or empty space then it is considered empty value
        if(val.isBlank() || val.isEmpty()){
            return "*This field is required.*";
        }
        //We return null because the checked value is not empty so no error text to supply
        return null;
    }

    public static String validateAddress(String address){
        String addressRegex = "^[A-Za-z\\d\\s-]+$";
        String capRegex = "^[A-Z]$";
        String emptiness = checkEmptiness(address);

        if(emptiness != null){
            return emptiness;
        }
        else if(address.toCharArray().length < 3){
            return "*Address should be 3 characters minimum.*";
        }
        else if(address.toCharArray().length > 85){
            return "*Address should be 85 characters maximum.*";
        }
        else if(!address.matches(addressRegex)){
            return "*Invalid address.*";
        }

        return null;
    }

    public static String validatePhone(String phone){
        String phoneRegex = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$";
        String emptiness = checkEmptiness(phone);

        if(emptiness != null){
            return emptiness;
        }
        else if(!phone.matches(phoneRegex)){
            return "*Invalid phone.*";
        }

        return null;
    }

    public static String validateEmail(String email){
        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        String emptiness = checkEmptiness(email);

        if(emptiness != null){
            return emptiness;
        }
        else if(!email.contains("@")){
            return "*Email must contain '@'*";
        }
        else if(!email.contains(".")){
            return "*Email must contain '.'*";
        }
//        else if(!email.matches(extRegex)){
//            return "*Domain extension is missing.";
//        }
        else if(!email.matches(emailRegex)){
            return "*Invalid email address.*";
        }

        return null;
    }

    /**
     * Check if the provided name follows names conventions
     * @param name String
     * @return String
     */
    public static String validateName(String name){
        String emptiness = checkEmptiness(name);

        if(emptiness != null){
            return emptiness;
        }
        else if(!String.valueOf(name.charAt(0)).matches("^[A-Z]$")){
            return "*Name should start with capital letter.*";
        }
        else if(name.length() < 3){
            return "*Three minimum characters are expected.*";
        }
        else if(!name.matches("^([A-Z][a-z]{2,})([\s][A-Z][a-z]{2,})*$")){
            return "*Invalid name.*";
        }


        return null;
    }
}
