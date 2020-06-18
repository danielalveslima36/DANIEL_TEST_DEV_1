package backend

import grails.gorm.transactions.Transactional

@Transactional
class CompanyService {

    def serviceMethod() {

    }

    void getStocks(String company, int hours){
        //SET TIME
        long start = System.currentTimeMillis()
        Calendar date = Calendar.getInstance()
        date.setTime(new Date())
        date.add(Calendar.HOUR_OF_DAY, -hours)
        //searching
        def newCompany = Company.findByName(company)
        def newStock = newCompany ? Stock.findAllByCompanyAndPriceDateBetween(newCompany, date.getTime(), new Date()): []

        print("\nSTOCK QUOTE: " + newStock.toString())
        print("\nTOTAL TIME: " + System.currentTimeMillis() - start)
        print("\nNUMBER OF QUOTES: " + newStock.size())
    }
}
