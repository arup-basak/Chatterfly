import bcrypt from 'bcrypt'

export const comparePassword = async (pass1, pass2) => {
    if(!pass1 && !pass2) 
        return false;
    const match = await bcrypt.compare(pass1, pass2);
    return match ? true : false;
}

export const getEncryptedPassword = async (password) => {
    const enPass = await bcrypt.hash(password, 10);
    return enPass;
}