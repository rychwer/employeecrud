package com.exam.richer.EmployeeCrud.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.exam.richer.EmployeeCrud.dto.EmployeeDTO;

public class EmployeeTemplate implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(EmployeeDTO.class).addTemplate("valida", new Rule() {{
            add("name", "Richer Santos");
            add("email", "teste@gmail");
            add("cep", "41310493");
        }});

        Fixture.of(EmployeeDTO.class).addTemplate("invalida", new Rule(){{
            add("name", null);
            add("email", null);
            add("cep", null);
        }});

    }
}