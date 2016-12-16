<?php

namespace App;

use Illuminate\Foundation\Auth\User as Authenticatable;

class User extends Authenticatable
{
	protected $fillable = [
		'name', 'email', 'password'
	];

	protected $hidden = [
		'password', 'remember_token'
	];

	protected $table = 'users';

	/**
	* check user exist
	* @param  string: $email, $name
	* @return boolean
	*/
	public function isExist ($email, $name) {
		$_where = array(
            'email'=> $email
        );

        $_or = array(
            'name' => $name
        );

        $_userexist = $this->find($_where, true, $_or);

        return $_userexist == null;
	}

	/**
	* get user datas
	* @param  string: email
	* @return object
	*/
	public function get ($email) {
		$_where = array(
			['email', '=', $email]
		);
		return $this->find($_where);
	}

	public function getbyid ($id) {
		$_where = array(
			['id', '=', $id]
		);
		return $this->find($_where);
	}

	/*
	 * get all users
	 */
	public function getUsers ($id, $name = null) {
		if ($name) {
			$_where = array(
					['id', '<>', $id],
					['name', 'like', '%' . $name . '%'],
			);
		} else {
			$_where = array(
					['id', '<>', $id]
			);
		}
        $_users = $this->find($_where, true, [], ['*'], 'name', 'ASC');

        return $_users;
	}
}
