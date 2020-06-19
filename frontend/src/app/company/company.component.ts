import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../service/company.service';
import { Company } from '../model/company';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {

  companies: Company[]

  constructor(private companyService: CompanyService) { }

  buttonClick: boolean = false;

  ngOnInit() {
  }

  showCompanies(){
    this.companyService.getCompanies().subscribe((companies: Company[]) => {
      this.companies = companies;
    })
    this.buttonClick = true
  }  

}
