
// 邮件接口
interface Email {
    void sendEmail(Customer customer);
}

// 商品广告邮件
class AdEmail implements Email {
    private String content;

    public AdEmail(String content) {
        this.content = content;
    }

    public void sendEmail(Customer customer) {
        System.out.println("正在发送给 " + customer.getName() + " 商品广告邮件: " + content);
    }
}

// 节日祝福邮件
class FestivalEmail implements Email {
    private String content;

    public FestivalEmail(String content) {
        this.content = content;
    }

    public void sendEmail(Customer customer) {
        System.out.println("正在发送给 " + customer.getName() + " 节日祝福邮件: " + content);
    }
}

// 商家邮件工厂
interface EmailFactory {
    Email createAdEmail(String content);
    Email createFestivalEmail(String content);
}

// 商家 A 邮件工厂
class EmailFactoryA implements EmailFactory {
    private static EmailFactoryA instance;

    private EmailFactoryA() {}

    public static synchronized EmailFactoryA getInstance() {
        if (instance == null) {
            instance = new EmailFactoryA();
        }
        return instance;
    }

    public Email createAdEmail(String content) {
        return new AdEmail(content + " 来自商家A");
    }

    public Email createFestivalEmail(String content) {
        return new FestivalEmail(content + " 来自商家A");
    }
}

// 商家 B 邮件工厂
class EmailFactoryB implements EmailFactory {
    private static EmailFactoryB instance;

    private EmailFactoryB() {}

    public static synchronized EmailFactoryB getInstance() {
        if (instance == null) {
            instance = new EmailFactoryB();
        }
        return instance;
    }

    public Email createAdEmail(String content) {
        return new AdEmail(content + " 来自商家B");
    }

    public Email createFestivalEmail(String content) {
        return new FestivalEmail(content + " 来自商家B");
    }
}

// 客户
class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// 程序入口
public class Main {
    public static void main(String[] args) {
        EmailFactoryA emailFactoryA = EmailFactoryA.getInstance();
        EmailFactoryB emailFactoryB = EmailFactoryB.getInstance();

        Customer customerA1 = new Customer("小李子");
        Customer customerA2 = new Customer("工作狂");
        Customer customerB1 = new Customer("大大怪");
        Customer customerB2 = new Customer("二愣子");

        Email ademailTocustomerA1 = emailFactoryA.createAdEmail("想要滑板吗");
        ademailTocustomerA1.sendEmail(customerA1);

        Email festivalemailTocustomerA1 = emailFactoryA.createFestivalEmail("生日快乐");
        festivalemailTocustomerA1.sendEmail(customerA1);

        Email ademailTocustomerA2 = emailFactoryA.createAdEmail("喜欢礼物吗");
        ademailTocustomerA2.sendEmail(customerA2);

        Email festivalemailTocustomerA2 = emailFactoryA.createFestivalEmail("女生节快乐");
        festivalemailTocustomerA2.sendEmail(customerA2);

        Email ademailTocustomerB1 = emailFactoryB.createAdEmail("贷款了解一下吗");
        ademailTocustomerB1.sendEmail(customerB1);

        Email festivalemailTocustomerB1 = emailFactoryB.createFestivalEmail("新年快乐");
        festivalemailTocustomerB1.sendEmail(customerB1);

        Email ademailTocustomerB2 = emailFactoryB.createAdEmail("想要什么商品吗");
        ademailTocustomerB2.sendEmail(customerB2);

        Email festivalemailTocustomerB2 = emailFactoryB.createFestivalEmail("祝你节日快乐");
        festivalemailTocustomerB2.sendEmail(customerB2);

    }
}