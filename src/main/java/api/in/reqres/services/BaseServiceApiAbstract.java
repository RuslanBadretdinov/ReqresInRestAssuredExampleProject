package api.in.reqres.services;

import api.in.reqres.specs.BaseSpecs;
import io.restassured.specification.RequestSpecification;

public abstract class BaseServiceApiAbstract {

    protected RequestSpecification requestSpec;

    public BaseServiceApiAbstract() {
    }

    protected void spec(String baseApiURL, String basePath) {
        this.requestSpec = BaseSpecs.requestSpec(baseApiURL, basePath);
        BaseSpecs.installSpec(this.requestSpec, BaseSpecs.responseSpec());
    }
}
