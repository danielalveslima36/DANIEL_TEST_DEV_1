package backend

import grails.gorm.transactions.Transactional

@Transactional
class CompanyService {

    def serviceMethod() {

    }

    //SEARCH FOR ALL STOCKS IN THE LAST HOURS
    def getStocks(String company, int hours){
        //SET TIME
        long start = System.currentTimeMillis()
        Calendar date = Calendar.getInstance()
        date.setTime(new Date())
        date.add(Calendar.HOUR_OF_DAY, -hours)
        //SEARCHING
        def newCompany = Company.findByName(company)
        def newStock = newCompany ? Stock.findAllByCompanyAndPriceDateBetween(newCompany, date.getTime(), new Date()): []

        print("\nSTOCK QUOTE: " + newStock.toString())
        print("\nTOTAL TIME: " + System.currentTimeMillis() - start)
        print("\nNUMBER OF QUOTES: " + newStock.size())
    }

    //RETURN THE AVERAGE OF STOCKS
    Double getMedia(List<Double> valor) {
        try {
            return getSoma(valor) / valor.size();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("The list has null values");
        }
    }

    //RETURN THE SUM OF STOCKS
    Double getSoma(List<Double> valor) {
        Double soma = 0;
        for (int i = 0; i < valor.size(); i++) {
            soma += valor.get(i);
        }
        return soma;
    }

    //RETURNS STANDARD DEVIATION OF STOCKS
    Double getDesvioPadrao(List<Double> valor) {
        Double media = getMedia(valor);
        int tam = valor.size();
        Double desvPadrao = 0;
        for (Double vlr : valor) {
            Double aux = vlr - media;
            desvPadrao += aux * aux;
        }
        return Math.sqrt(desvPadrao / (tam - 1));
    }

    //RETURNS A LIST OF COMPANIES WITH SEGMENT AND STANDART DEVIATION
    def getCompanies(){
        List<Map> listCompanies = []
        def companies = Company.list()
        for(Company company : companies){
            Map mapCompany = [:]
            mapCompany['Name'] = company.name
            mapCompany['Segment'] = company.segment
            mapCompany['Deviation'] = getDesvioPadrao(Stock.findAllByCompany(company)?.collect{it.price})
            listCompanies.add(mapCompany)
        }
        return listCompanies
    }
}
