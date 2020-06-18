package backend

import org.apache.tomcat.jni.Local

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

class BootStrap {
    CompanyService companyService
    def init = { servletContext ->
        //CREATING COMPANIES
        Company company1 = new Company(name: "Amazon", segment: "Eletronicos")
        company1.stocks = []
        Company company2 = new Company(name: "Ford", segment: "Veiculos")
        company2.stocks = []
        Company company3 = new Company(name: "Nubank", segment: "Financeiro")
        company3.stocks = []



        Calendar start = Calendar.getInstance()
        start.setTime(new Date())
        start.set(Calendar.HOUR_OF_DAY, 10)
        start.set(Calendar.MINUTE, 00)
        start.set(Calendar.SECOND, 00)

        Calendar end = Calendar.getInstance()
        end.setTime(start.getTime())
        end.add(Calendar.DATE, 30)
        end.set(Calendar.HOUR_OF_DAY, 18)
        end.set(Calendar.MINUTE, 00)
        end.set(Calendar.SECOND, 00)


        initData(start, end, company1)
        initData(start, end, company2)
        initData(start, end, company3)

    }
        def destroy = {
        }


    void initData(Calendar start, Calendar end, Company company){
        Random random = new Random()
        Calendar inicio  = Calendar.getInstance()
        Calendar fim  = Calendar.getInstance()
        inicio.setTime(start.getTime())
        fim.setTime(end.getTime())
        while(inicio.before(fim)){
            while(inicio.get(Calendar.HOUR_OF_DAY) < fim.get(Calendar.HOUR_OF_DAY)){
                Double newPrice = random.nextDouble()
                Stock stock = new Stock(price: newPrice, priceDate: inicio.getTime(), company: company)
                company.stocks.add(stock)
                inicio.add(Calendar.MINUTE, 1)
            }
            inicio.set(Calendar.HOUR_OF_DAY, 10)
            inicio.set(Calendar.MINUTE, 00)
            inicio.set(Calendar.SECOND, 00)
            inicio.add(Calendar.DATE, 1)
        }
        company.save()
    }

}
