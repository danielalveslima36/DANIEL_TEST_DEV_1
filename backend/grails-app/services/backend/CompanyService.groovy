package backend

import grails.gorm.transactions.Transactional

@Transactional
class CompanyService {

    def serviceMethod() {

    }

    def getStocks(String company, int hours){
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

    Double getMedia(List<Double> valor) {
        try {
            return getSoma(valor) / valor.size();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("The list has null values");
        }
    }

    Double getSoma(List<Double> valor) {
        Double soma = 0;
        for (int i = 0; i < valor.size(); i++) {
            soma += valor.get(i);
        }
        return soma;
    }

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

    def getCompanies(){
        Map<String,Map> mapCompanies = [:]
        def companies = Company.list()
        for(Company company : companies){
            Map mapCompany = [:]
            mapCompany['Name'] = company.name
            mapCompany['Segment'] = company.segment
            mapCompany['Deviation'] = getDesvioPadrao(Stock.findAllByCompany(company)?.collect{it.price})
            mapCompanies[company.id.toString()] = mapCompany
        }
        return mapCompanies
    }
}
