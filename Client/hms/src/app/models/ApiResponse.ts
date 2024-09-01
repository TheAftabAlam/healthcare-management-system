import { CommonListTO } from "./CommonListTO";

export interface ApiResponse<T> {
    status: number;
    message: string;
    path: string;
    code: string;
    data: any;
    errorList?: any;
  }
  
