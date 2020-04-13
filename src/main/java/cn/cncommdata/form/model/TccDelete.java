package cn.cncommdata.form.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @DESCRIPTION: new class
 * @AUTHOR: leimin
 * @DATE: 2019/12/17 17:29
 */
@lombok.Data
@Document(collection = "tccDelete")
public class TccDelete extends FormData {
}
