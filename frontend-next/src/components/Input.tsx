'use client'

import React from 'react'
import { TextField } from '@radix-ui/themes'

const Input = () => {
    return (
        <TextField.Root>
            <TextField.Input placeholder="Type Your Message Here…" />
        </TextField.Root>
    )
}

export default Input