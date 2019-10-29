package com.example.yeop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.criterion.Projection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import com.example.yeop.entity.Finance;
import com.example.yeop.entity.QFinance;
import com.example.yeop.entity.QInstitute;
import com.example.yeop.vo.TotalByYear;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class FinanceRepositoryImpl extends QuerydslRepositorySupport implements FinanceRepositoryCustom {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public FinanceRepositoryImpl() {
        super(Finance.class);
    }
	
	@Override
	public List<TotalByYear> findTotalByYear() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
		QFinance finance = QFinance.finance;
		QInstitute institute = QInstitute.institute;
		
		return queryFactory.select(Projections.constructor(TotalByYear.class, finance.year, institute.instituteName, finance.value.sum()))
				.from(finance)
				.innerJoin(institute)
				.on(finance.instituteCode.eq(institute.instituteCode))
				.groupBy(finance.year, finance.instituteCode)
				.fetch();
	}
	
	@Override
	public List<TotalByYear> findTotalByYearAndName(String bank_name, Integer start_year, Integer end_year) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
		QFinance finance = QFinance.finance;
		QInstitute institute = QInstitute.institute;
		
		return queryFactory.select(Projections.constructor(TotalByYear.class, finance.year, institute.instituteName, finance.value.sum()))
				.from(finance)
				.innerJoin(institute)
				.on(finance.instituteCode.eq(institute.instituteCode)
						.and(institute.instituteName.eq(bank_name)))
				.where(finance.year.goe(start_year)
						.and(finance.year.loe(end_year)))
				.groupBy(finance.year)
				.fetch();
	}
	
	
	
}
