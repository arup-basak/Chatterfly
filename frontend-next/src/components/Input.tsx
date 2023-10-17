'use client'

import {useState} from 'react'
import { TextField } from '@radix-ui/themes'

interface InputProps {
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void
    defaultValue?: string
    placeholder: string
    onEnterEvent?: () => void
}

const Input = (props: InputProps) => {
    const [value, setValue] = useState<string>(props.defaultValue || "");

    const handleChangeEvent = (e: React.ChangeEvent<HTMLInputElement>) => {
        setValue(e.target.value);
    }

    return (
        <TextField.Root>
            <TextField.Input
                placeholder={props.placeholder}
                defaultValue={value}
                onChange={handleChangeEvent}
            />
        </TextField.Root>
    )
}

export default Input