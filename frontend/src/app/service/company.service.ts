import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Company } from '../model/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  //BASE URL
  readonly url = "http://localhost:8080/company/getCompanyDetails"


  constructor(private httpClient: HttpClient) { }

  //RETURNS ALL THE COMPANIES
  getCompanies(){
   return this.httpClient.get<Company[]>(this.url)
  }
}
