package assessment.Ques7;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.StandardGenerator;

public class CustomIdGenerator implements StandardGenerator {
    private final String DEFAULT_SEQUENCE_NAME="my_seq";
    static int i=0;
    @Override

    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        i++;
        return i;
    }
}