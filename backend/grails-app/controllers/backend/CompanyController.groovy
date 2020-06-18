package backend

import grails.converters.JSON

class CompanyController {

    CompanyService companyService

    def index() {

    }

    def getCompanyDetails(){
        render(companyService.getCompanies() as JSON)
    }

}
