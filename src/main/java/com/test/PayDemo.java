package com.test;

import java.util.ArrayList;
import java.util.List;
 
public class PayDemo {
 
	public abstract class Market {
		String name;
 
		public abstract void add(Market m);
 
		public abstract void remove(Market m);
 
		public abstract void PayByCard();
	}
 
	// 分店 下面可以有加盟店
	public class MarketBranch extends Market {
		// 加盟店列表
		List<Market> list = new ArrayList<PayDemo.Market>();
 
		public MarketBranch(String s) {
			this.name = s;
		}
 
		@Override
		public void add(Market m) {
			// TODO Auto-generated method stub
			list.add(m);
		}
 
		@Override
		public void remove(Market m) {
			// TODO Auto-generated method stub
			list.remove(m);
		}
 
		// 消费之后，该分店下的加盟店自动累加积分
		@Override
		public void PayByCard() {
			// TODO Auto-generated method stub
			System.out.println(name + "消费,积分已累加入该会员卡");
			for (Market m : list) {
				m.PayByCard();
			}
		}
	}
 
	// 加盟店 下面不在有分店和加盟店，最底层
	public class MarketJoin extends Market {
		public MarketJoin(String s) {
			this.name = s;
 
		}
 
		@Override
		public void add(Market m) {
			// TODO Auto-generated method stub
 
		}
 
		@Override
		public void remove(Market m) {
			// TODO Auto-generated method stub
 
		}
 
		@Override
		public void PayByCard() {
			// TODO Auto-generated method stub
			System.out.println(name + "消费,积分已累加入该会员卡");
		}
	}
 
	public static void main(String[] args) {
		PayDemo demo = new PayDemo();
		
		MarketBranch rootBranch = demo.new MarketBranch("总店");
		MarketBranch qhdBranch = demo.new MarketBranch("深圳分店");
		MarketJoin hgqJoin = demo.new MarketJoin("深圳分店一福田区加盟店");
		MarketJoin btlJoin = demo.new MarketJoin("深圳分店二南山科技园加盟店");
		
		qhdBranch.add(hgqJoin);
		qhdBranch.add(btlJoin);
		rootBranch.add(qhdBranch);
		rootBranch.PayByCard();
	}
}
