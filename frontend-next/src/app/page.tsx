'use client'

import React, { useState, useEffect } from 'react'
import Message from '@/components/Message'
import ChatUsers from '@/components/chat/ChatUsers'
import { TextField } from '@radix-ui/themes'
import socket from '@/utils/socket'
import axios from 'axios'

const Home = () => {
  const [inputValue, setInputValue] = useState("");
  const serverBaseUrl = 'http://localhost:3001'
  // const serverBaseUrl = process.env.NEXT_JS_PUBLIC_BACKEND_URL as string;

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  }

  const handleEnterClick = () => {
    setInputValue("")
  }

  useEffect(() => {
    const onConnect = () => {
      console.log("Connected")
    }
    socket.on('connect', onConnect);
    socket.onAny((data) => {
      console.log(data)
    })
  })

  useEffect(() => {
    axios.post(`${serverBaseUrl}/get-chats`, {
      username: 'arupbasak'
    })
    .then((response) => {
      const jsonData = response.data;
      console.log(jsonData)
    })
  })

  return (
    <div className='grid grid-cols-[300px_1fr]'>
      <div className='flex flex-col overflow-scroll'>

      </div>
      <div className='flex flex-col justify-between h-screen overflow-y-hidden'>
        <div>
          Arup Basak
        </div>
        <div className='overflow-scroll'>
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
          <Message />
        </div>
        <div>
          <TextField.Root>
            <TextField.Input
              placeholder="Type Your Message Here..."
              defaultValue={inputValue}
              onChange={handleInputChange}
              onKeyDown={(e) => { if (e.key === 'Enter') handleEnterClick() }}
            />
          </TextField.Root>
        </div>
      </div>
    </div>
  )
}

export default Home