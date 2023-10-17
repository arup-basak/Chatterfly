'use client'

import Input from '@/components/Input'
import React, { useEffect } from 'react'
import Message from '@/components/Message'
import ChatUsers from '@/components/chat/ChatUsers'
import socket from '@/utils/socket'

const Home = () => {
  useEffect(() => {
    const onConnect = () => {
      console.log("Connected")
    }
    socket.on('connect', onConnect);
    socket.onAny((data) => {
      console.log(data)
    })
  })
  return (
    <div className='grid grid-cols-[300px_1fr]'>
      <div className='flex flex-col overflow-scroll'>
        <ChatUsers name='Arup Basak' />
        <ChatUsers name='Arup Basak' />
        <ChatUsers name='Arup Basak' />
        <ChatUsers name='Arup Basak' />
        <ChatUsers name='Arup Basak' />
        <ChatUsers name='Arup Basak' />
        <ChatUsers name='Arup Basak' />
        <ChatUsers name='Arup Basak' />
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
          <Input />
        </div>
      </div>
    </div>
  )
}

export default Home