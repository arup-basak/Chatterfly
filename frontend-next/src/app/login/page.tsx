'use client'

import { useState } from 'react'
import { Button, TextField, Flex, Container } from '@radix-ui/themes';
import axios from 'axios';
import LoginInterface from '@/interface/login.interface';
import { saveLoginCookie } from './action'

const Page = () => {
  const [user, setUser] = useState<string>("")
  const [password, setPassword] = useState<string>("")

  const handleForgetPasswordClick = () => {

  }

  const handleRequest = async (type: 'login' | 'signup') => {
    // const baseUrl = process.env.NEXT_JS_PUBLIC_BACKEND_URL as string 
    const baseUrl = 'http://172.17.0.1:3001';
    const url = `${baseUrl}/${type}`

    const response = await axios.post(url, {
      username: user,
      password: password
    })
    const jsonData = response.data as LoginInterface;
    saveLoginCookie(jsonData).then(() => {
      console.log("saved")
    })
  }

  return (
    <Container>
      <Flex direction="column" gap="3" style={{ maxWidth: 400 }}>
        <TextField.Root>
          <TextField.Input placeholder="Type User…" size="3" onChange={(e) => setUser(e.target.value)} />
        </TextField.Root>
        <TextField.Root>
          <TextField.Input placeholder="Type Password…" size="3" onChange={(e) => setPassword(e.target.value)} />
        </TextField.Root>
        <Flex direction="row">
          <Button onClick={() => handleRequest('signup')}>SignUp</Button>
          <Button onClick={() => handleRequest('login')}>Login</Button>
        </Flex>
      </Flex>
    </Container>
  )
}

export default Page