import { NextRequest, NextResponse } from 'next/server';

export function middleware(request: NextRequest): NextResponse {
  let cookie = request.cookies
  if (request.nextUrl.pathname === '/' && !cookie.get('token')) {
    return NextResponse.redirect(new URL('/login', request.url));
  }
  else if(request.nextUrl.pathname === '/login' && cookie.get('token')) {
    return NextResponse.redirect(new URL('/', request.url));
  }
  return NextResponse.next();
}
