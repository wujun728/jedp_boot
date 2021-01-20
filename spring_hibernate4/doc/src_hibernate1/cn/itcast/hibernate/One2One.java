package cn.itcast.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.itcast.hibernate.domain.IdCard;
import cn.itcast.hibernate.domain.Person;

public class One2One {
	public static void main(String[] args) {
		add();
		add();
		System.out.println("111111111111");
		// query(1);
		ncpp();
	}

	static void ncpp() {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from IdCard");
		List<IdCard> ics = q.list();
		for (IdCard ic : ics) {
			System.out.println(ic.getPerson().getName());
		}
		s.close();
	}

	static Person query(int id) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			Person p = (Person) s.get(Person.class, id);
			// System.out.print(p.getIdCard());
			// IdCard idCard = (IdCard) s.get(IdCard.class, id);
			// System.out.println(idCard.getPerson());
			tx.commit();
			return null;
		} finally {
			if (s != null)
				s.close();
		}
	}

	static Person add() {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getSession();
			IdCard idCard = new IdCard();
			idCard.setUsefulLife(new Date());

			Person p = new Person();
			p.setName("p1");
			// p.setIdCard(idCard);

			idCard.setPerson(p);

			tx = s.beginTransaction();
			s.save(p);
			s.save(idCard);
			tx.commit();
			return p;
		} finally {
			if (s != null)
				s.close();
		}
	}
}
