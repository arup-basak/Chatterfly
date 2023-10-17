'use server'

import LoginInterface from '@/interface/login.interface';
import { cookies } from 'next/headers'

export async function saveLoginCookie(response: LoginInterface) {
    if (!response.success) {
        return;
    }
    cookies().set('token', response.data?.token as string);
    cookies().set('userId', response.data?.userId as string);
}