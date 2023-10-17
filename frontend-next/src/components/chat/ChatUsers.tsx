import React from 'react'

interface ChatUserProps {
    name: string
}

const ChatUsers = (props: ChatUserProps) => {
  return (
    <div className='m-1 p-3 bg-blue-300 rounded'>
        {props.name}
    </div>
  )
}

export default ChatUsers