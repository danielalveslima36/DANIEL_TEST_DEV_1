package backend

import grails.converters.JSON

class CompanyController {

    CompanyService companyService

    def index() {

    }
    //RETURNS A LIST OF COMPANIES IN FORMAT JSON
    def getCompanyDetails(){
        render(companyService.getCompanies() as JSON)
    }

}
