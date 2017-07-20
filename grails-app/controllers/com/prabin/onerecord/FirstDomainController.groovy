package com.prabin.onerecord

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FirstDomainController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FirstDomain.list(params), model:[firstDomainCount: FirstDomain.count()]
    }

    def show(FirstDomain firstDomain) {
        respond firstDomain
    }

    def create() {
        respond new FirstDomain(params)
    }

    @Transactional
    def save(FirstDomain firstDomain) {
        if (firstDomain == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (firstDomain.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond firstDomain.errors, view:'create'
            return
        }

        firstDomain.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'firstDomain.label', default: 'FirstDomain'), firstDomain.id])
                redirect firstDomain
            }
            '*' { respond firstDomain, [status: CREATED] }
        }
    }

    def edit(FirstDomain firstDomain) {
        respond firstDomain
    }

    @Transactional
    def update(FirstDomain firstDomain) {
        if (firstDomain == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (firstDomain.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond firstDomain.errors, view:'edit'
            return
        }

        firstDomain.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'firstDomain.label', default: 'FirstDomain'), firstDomain.id])
                redirect firstDomain
            }
            '*'{ respond firstDomain, [status: OK] }
        }
    }

    @Transactional
    def delete(FirstDomain firstDomain) {

        if (firstDomain == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        firstDomain.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'firstDomain.label', default: 'FirstDomain'), firstDomain.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'firstDomain.label', default: 'FirstDomain'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
