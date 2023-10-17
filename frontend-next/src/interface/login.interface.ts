export default interface LoginInterface {
    data?: DataInterface,
    error?: string,
    success: boolean
}

interface DataInterface {
    userId: string,
    token: string
}